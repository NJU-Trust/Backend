package nju.trust.payloads.flea;

import com.fasterxml.jackson.annotation.JsonProperty;
import nju.trust.entity.flea.Trade;

import javax.validation.constraints.NotNull;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/20
 * @Todo:
 */
public class TradeInfo {
    @NotNull
    private Long id;

    private String goodsName;

    @JsonProperty("goodsType")
    private String goodsType;

    private String goodsDesc;

    private String contact;

    private String pic;

    private int price;


    public TradeInfo(Trade trade) {
        this.id = trade.getId();
        this.goodsName = trade.getGoodsName();
        this.goodsType = trade.getGoodsType();
        this.goodsDesc = trade.getGoodsDesc();
        this.contact = trade.getContact();
        this.pic = trade.getGoodsPic();
        this.price = trade.getGoodsPrice();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
