package nju.trust.payloads.user;

/**
 * Author: J.D. Liao
 * Date: 2018/8/17
 * Description:
 */
public class BorrowingStatistics {

    /**
     * 借款总额度
     */
    private Double totalAmount;
    /**
     * 当前借款总额
     */
    private Double currentAmount;
    /**
     * 剩余借款额度
     */
    private Double remainingAmount;

    public BorrowingStatistics(Double totalAmount, Double currentAmount, Double remainingAmount) {
        this.totalAmount = totalAmount;
        this.currentAmount = currentAmount;
        this.remainingAmount = remainingAmount;
    }

    @Override
    public String toString() {
        return "BorrowingStatistics{" +
                "totalAmount=" + totalAmount +
                ", currentAmount=" + currentAmount +
                ", remainingAmount=" + remainingAmount +
                '}';
    }
}
