package nju.trust.payloads.user;

/**
 * Author: J.D. Liao
 * Date: 2018/8/17
 * Description:
 */
public class AssetStatistics {

    /**
     * 总资产
     */
    private Double totalAssets;
    /**
     * 累计资产
     */
    private Double cumulativeIncome;
    /**
     * 可用余额
     */
    private Double balance;

    public AssetStatistics(Double totalAssets, Double cumulativeIncome, Double balance) {
        this.totalAssets = totalAssets;
        this.cumulativeIncome = cumulativeIncome;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AssetStatistics{" +
                "totalAssets=" + totalAssets +
                ", cumulativeIncome=" + cumulativeIncome +
                ", balance=" + balance +
                '}';
    }
}
