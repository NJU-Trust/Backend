package nju.trust.payloads.investment;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import nju.trust.payloads.target.TargetInfo;

import java.time.LocalDateTime;

/**
 * Author: J.D. Liao
 * Date: 2018/8/17
 * Description:
 */
public class InvestmentInfo {

    @JsonUnwrapped
    private TargetInfo targetInfo;

    private LocalDateTime investmentTime;

    /**
     * 投资金额
     */
    private Double moneyIn;

    private Double moneyBack;

    public InvestmentInfo(TargetInfo targetInfo, LocalDateTime investmentTime,
                          Double moneyIn, Double moneyBack) {
        this.targetInfo = targetInfo;
        this.investmentTime = investmentTime;
        this.moneyIn = moneyIn;
        this.moneyBack = moneyBack;
    }

    @Override
    public String toString() {
        return "InvestmentInfo{" +
                "targetInfo=" + targetInfo +
                ", investmentTime=" + investmentTime +
                ", moneyIn=" + moneyIn +
                ", moneyBack=" + moneyBack +
                '}';
    }
}
