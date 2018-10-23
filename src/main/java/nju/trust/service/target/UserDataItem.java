package nju.trust.service.target;

/**
 * Author: J.D. Liao
 * Date: 2018/10/23
 * Description:
 */
public class UserDataItem {

    private double surplus;

    private double netAssets;

    private double engel;

    private double rigid;

    private double assetLiabilityRatio;

    private double totalLiabilities;

    private double solvency;

    private double leverage;

    private double monthConsumptionRatio;

    private double monthSavingRatio;

    public UserDataItem(double surplus, double netAssets, double engel,
                        double rigid, double assetLiabilityRatio,
                        double totalLiabilities, double solvency, double leverage,
                        double monthConsumptionRatio, double monthSavingRatio) {
        this.surplus = surplus;
        this.netAssets = netAssets;
        this.engel = engel;
        this.rigid = rigid;
        this.assetLiabilityRatio = assetLiabilityRatio;
        this.totalLiabilities = totalLiabilities;
        this.solvency = solvency;
        this.leverage = leverage;
        this.monthConsumptionRatio = monthConsumptionRatio;
        this.monthSavingRatio = monthSavingRatio;
    }

    public double getSurplus() {
        return surplus;
    }

    public double getNetAssets() {
        return netAssets;
    }

    public double getEngel() {
        return engel;
    }

    public double getRigid() {
        return rigid;
    }

    public double getAssetLiabilityRatio() {
        return assetLiabilityRatio;
    }

    public double getTotalLiabilities() {
        return totalLiabilities;
    }

    public double getSolvency() {
        return solvency;
    }

    public double getLeverage() {
        return leverage;
    }

    public double getMonthConsumptionRatio() {
        return monthConsumptionRatio;
    }

    public double getMonthSavingRatio() {
        return monthSavingRatio;
    }
}
