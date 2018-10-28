package nju.trust.entity.target;

public enum SmallProjectClassification {

    /**
     * 日常费用
     */
    DAILY_EXPENSE("日常费用"),
    /**
     * 演唱会看比赛看剧音乐会等
     */
    CONCERT("演唱会"),
    /**
     * 游戏娱乐电影音乐
     */
    GAME_MOVIE_MUSIC("游戏娱乐电影音乐"),
    /**
     * 旅游
     */
    TRAVEL("旅游"),
    /**
     * 购买电子设备
     */
    ELECTRONIC_DEVICE("购买电子设备"),
    /**
     * 其他购买项比如化妆品衣服鞋
     */
    OTHER("其他"),
    /**
     * 出国所需考试的相关成绩单
     */
    TEST_TRANSCRIPT("出国考试"),
    /**
     * 各类考证
     */
    CERTIFICATE_TEST("各类考证"),
    /**
     * 外出学习培训的培训证明或信息
     */
    TRAIN("外出学习培训"),
    /**
     * 职业资格证
     */
    PROFESSIONAL_QUALIFICATION("职业资格证"),
    /**
     * 交换生
     */
    EXCHANGE_PROJECT("交换生");

    private String str;

    SmallProjectClassification(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
