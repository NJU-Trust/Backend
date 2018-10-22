package nju.trust.config;

import nju.trust.dao.user.UserCrossCheckRepository;
import nju.trust.dao.user.UserRepository;
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
        List<User> users = userCrossCheckRepository.findDistinctByEndDate(date);
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
            //>=5 计算问卷得分
            if(ids.size()>=5){
                double score = 0;
                int totalWeight = 0;
                for(int k=0;k<indexOfIds.size();k++){
                    int weight = calculateWeight(creditCrossChecks.get(indexOfIds.get(k)));
                    int totalScore = calculateTotalScore(creditCrossChecks.get(indexOfIds.get(k)));
                    totalWeight+=weight;
                    score+=weight*totalScore;
                }
                score = score*1.0/totalWeight;

                double creditScore = user.getCreditScore();

                double X = Math.abs(score-creditScore);

                if(X<=20){
                    //不调整信用分数
                }else if(30<X&&X<=35){
                    //+-3
                    if(score>creditScore){
                        //+3
                        user.setCreditScore(creditScore+3);
                        userRepository.save(user);
                    }else{
                        //-3
                        user.setCreditScore(creditScore-3);
                        userRepository.save(user);
                    }
                }else if(X>=35){
                    //+-5
                    if(score>creditScore){
                        //+5
                        user.setCreditScore(creditScore+5);
                        userRepository.save(user);
                    }else{
                        //-5
                        user.setCreditScore(creditScore-5);
                        userRepository.save(user);
                    }
                }
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
        }else{
            score89-=5;
        }
        totalScore+=score89;

        return totalScore;
    }

}
