package nju.trust.payloads.investment;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
public class InvestmentStrategy {

    private Long id;

    private Double investmentRatio;

    private Double investmentMoney;

    public InvestmentStrategy(Long id, Double investmentRatio, Double investmentMoney) {
        this.id = id;
        this.investmentRatio = investmentRatio;
        this.investmentMoney = investmentMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getInvestmentRatio() {
        return investmentRatio;
    }

    public void setInvestmentRatio(Double investmentRatio) {
        this.investmentRatio = investmentRatio;
    }

    public Double getInvestmentMoney() {
        return investmentMoney;
    }

    public void setInvestmentMoney(Double investmentMoney) {
        this.investmentMoney = investmentMoney;
    }
}
