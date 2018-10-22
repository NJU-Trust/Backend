package nju.trust.payloads.flea;

import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/21
 * @Todo:
 */
public class TradeInfoPage {
    int total;
    List<TradeInfo> tradeInfoList;

    public TradeInfoPage(int total, List<TradeInfo> tradeInfoList) {
        this.total = total;
        this.tradeInfoList = tradeInfoList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TradeInfo> getTradeInfoList() {
        return tradeInfoList;
    }

    public void setTradeInfoList(List<TradeInfo> tradeInfoList) {
        this.tradeInfoList = tradeInfoList;
    }
}
