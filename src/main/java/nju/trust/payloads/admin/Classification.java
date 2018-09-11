package nju.trust.payloads.admin;

import nju.trust.entity.target.LargeProjectClassification;
import nju.trust.entity.target.SmallProjectClassification;

/**
 * @Author: 许杨
 * @Description: 资金去向
 * @Date: 2018/9/11
 */
public enum Classification {
    EXCHANGE_PROJECT("交换生"),
    GMAT("GMAT"),
    TOEFL("TOEFL"),
    IELTS("IELTS"),
    DAILY_EXPENSE("日常费用"),
    CONCERT("演唱会看比赛看剧音乐会等"),
    GAME_MOVIE_MUSIC("游戏娱乐电影音乐"),
    TRAVEL("旅游"),
    ELECTRONIC_DEVICE("购买电子设备"),
    OTHER("其他购买项比如化妆品衣服鞋"),
    TEST_TRANSCRIPT("出国所需考试的相关成绩单"),
    CERTIFICATE_TEST("各类考证"),
    TRAIN("外出学习培训的培训证明或信息"),
    PROFESSIONAL_QUALIFICATION("职业资格证");

    private String str;

    Classification(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static Classification getClassification(LargeProjectClassification largeProjectClassification) {
        switch (largeProjectClassification) {
            case GMAT:return Classification.GMAT;
            case IELTS:return Classification.IELTS;
            case TOEFL:return Classification.TOEFL;
            case CERTIFICATE_TEST:return Classification.CERTIFICATE_TEST;
            case EXCHANGE_PROJECT:return Classification.EXCHANGE_PROJECT;
            default:return null;
        }
    }

    public static Classification getClassification(SmallProjectClassification smallProjectClassification) {
        switch (smallProjectClassification) {
            case DAILY_EXPENSE:return Classification.DAILY_EXPENSE;
            case CONCERT:return Classification.CONCERT;
            case GAME_MOVIE_MUSIC:return Classification.GAME_MOVIE_MUSIC;
            case TRAVEL:return Classification.TRAVEL;
            case ELECTRONIC_DEVICE:return Classification.ELECTRONIC_DEVICE;
            case OTHER:return Classification.OTHER;
            case TEST_TRANSCRIPT:return Classification.TEST_TRANSCRIPT;
            case CERTIFICATE_TEST:return Classification.CERTIFICATE_TEST;
            case TRAIN:return Classification.TRAIN;
            case PROFESSIONAL_QUALIFICATION:return Classification.PROFESSIONAL_QUALIFICATION;
            case EXCHANGE_PROJECT:return Classification.EXCHANGE_PROJECT;
            default:return null;
        }
    }
}
