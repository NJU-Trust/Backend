package nju.trust.config;

import nju.trust.dao.user.UserCrossCheckRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CreditRating;
import nju.trust.entity.user.CreditCrossCheck;
import nju.trust.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/22
 */
@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class CrossCheckConfig {

    private UserCrossCheckRepository userCrossCheckRepository;
    private UserRepository userRepository;

    @Autowired
    public CrossCheckConfig(UserCrossCheckRepository userCrossCheckRepository, UserRepository userRepository) {
        this.userCrossCheckRepository = userCrossCheckRepository;
        this.userRepository = userRepository;
    }


    //3.添加定时任务,每天凌晨四点进行交叉检验处理
    @Scheduled(cron = "0 0 4 * * ?")
    private void configureTasks() {
        LocalDate date = LocalDate.now();
        //今天要计算的人员名单
        List<CreditCrossCheck> creditCrossCheckList = userCrossCheckRepository.findDistinctByEndDate(date);
        List<User> users = new ArrayList<>();
        for(int i=0;i<creditCrossCheckList.size();i++){
            if(users.indexOf(creditCrossCheckList.get(i).getUser())<0){
                users.add(creditCrossCheckList.get(i).getUser());
            }
        }

        for(int i=0;i<users.size();i++){
            //当前user
            User user = users.get(i);

            //得到提交检验问卷的人
            List<CreditCrossCheck> creditCrossChecks = userCrossCheckRepository.findAllByUserUsernameAndEndDate(user.getUsername(),date);
            ArrayList<Long> ids = new ArrayList<>();
            ArrayList<Integer> indexOfIds = new ArrayList<>();
            for(int j=0;j<creditCrossChecks.size();j++){
                if(creditCrossChecks.get(j).isDone()&&ids.indexOf(creditCrossChecks.get(j).getId())<0){
                    ids.add(creditCrossChecks.get(j).getId());
                    indexOfIds.add(new Integer(j));
                }
                //valid = false
                creditCrossChecks.get(j).setValid(false);
                userCrossCheckRepository.save(creditCrossChecks.get(j));
            }

            /*
            System.out.println(user.getUsername());
            System.out.println(ids);
            System.out.println(indexOfIds);
            */

            //>=5 计算问卷得分
            if(ids.size()>=5){
                double score = 0;
                double totalWeight = 0;
                for(int k=0;k<indexOfIds.size();k++){
                    int weight = calculateWeight(creditCrossChecks.get(indexOfIds.get(k)));
                    int totalScore = calculateTotalScore(creditCrossChecks.get(indexOfIds.get(k)));
                    /*
                    System.out.println("totalScore:"+totalScore+"   weight:"+weight);
                    */
                    totalWeight+=weight;
                    score+=weight*totalScore;
                }
                score = score*1.0/totalWeight;

                /*
                System.out.println("calculate score:"+score);
                */

                double creditScore = user.getCreditScore();

                double X = Math.abs(score-creditScore);

                if(X<=20){
                    //不调整信用分数
                }else if(30<X&&X<=35){
                    //+-3
                    if(score>creditScore){
                        //+3
                        user.setCreditScore(creditScore+3);
                    }else{
                        //-3
                        user.setCreditScore(creditScore-3);
                    }
                }else if(X>=35){
                    //+-5
                    if(score>creditScore){
                        //+5
                        user.setCreditScore(creditScore+5);
                    }else{
                        //-5
                        user.setCreditScore(creditScore-5);
                    }
                }
                //评级修改
                if(user.getCreditScore()<40){
                    user.setCreditRating(CreditRating.D);
                }else if(40<=user.getCreditScore()&&user.getCreditScore()<60){
                    user.setCreditRating(CreditRating.C);
                }else if(60<=user.getCreditScore()&&user.getCreditScore()<75){
                    user.setCreditRating(CreditRating.B);
                }else if (75<=user.getCreditScore()&&user.getCreditScore()<90){
                    user.setCreditRating(CreditRating.A);
                }else if (90<=user.getCreditScore()&&user.getCreditScore()<=100){
                    user.setCreditRating(CreditRating.AA);
                }
                user.setCrossScore(score);
                userRepository.save(user);

            }else{
                //回归未填写状态
                user.setCrossScore(-1.0);
                userRepository.save(user);
            }

        }
    }

    private int calculateWeight(CreditCrossCheck creditCrossCheck){
        int weight = 0;
        switch (creditCrossCheck.getQ1()){
            case 1: weight+=12; break;
            case 2: weight+=10; break;
            case 3:weight+=8; break;
            case 4:weight+=10; break;
            default:break;
        }
        switch (creditCrossCheck.getQ2()){
            case 1: weight+=3; break;
            default:break;
        }
        switch (creditCrossCheck.getQ5()){
            case 1: weight+=3; break;
            default:break;
        }
        return weight;
    }

    private int calculateTotalScore(CreditCrossCheck creditCrossCheck){
        int totalScore = 0;
        if(creditCrossCheck.getQ1()==4){
            totalScore-=10;
        }
        switch (creditCrossCheck.getQ3()){
            case 1: totalScore+=25; break;
            case 2: totalScore+=25; break;
            case 3:totalScore+=20; break;
            case 4:totalScore+=10; break;
            case 5:totalScore+=15;break;
            default:break;
        }
        switch (creditCrossCheck.getQ4()){
            case 1: totalScore+=25; break;
            case 2: totalScore+=20; break;
            case 3:totalScore+=10; break;
            default:break;
        }
        switch (creditCrossCheck.getQ6()){
            case 1: totalScore+=25; break;
            case 2: totalScore+=25; break;
            case 3:totalScore+=15; break;
            case 4:totalScore+=10; break;
            case 5:totalScore+=15;break;
            default:break;
        }

        int score89 = 0;
        score89+=(creditCrossCheck.getQ8()+1)*2.5;
        if(creditCrossCheck.getQ8()>creditCrossCheck.getQ9()){
            score89+=5;
            if(score89>25){
                score89=25;
            }
        }else if(creditCrossCheck.getQ8()<creditCrossCheck.getQ9()){
            score89-=5;
        }
        totalScore+=score89;

        return totalScore;
    }

}
