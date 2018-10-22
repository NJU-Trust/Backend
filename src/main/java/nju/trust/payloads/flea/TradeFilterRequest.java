package nju.trust.payloads.flea;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/20
 * @Todo:
 */
public class TradeFilterRequest {
    @JsonProperty("goodsTypes")
    private List<String> goodsTypes;

    @NotNull
    private int size;

    @NotNull
    private int page;

    @NotNull
    private String properties;

    private String goodsName;
    @JsonProperty("isMine")
    private boolean isMine;
    @JsonProperty("isSelling")
    private boolean isSelling;
    @JsonProperty("isSellingAll")
    private boolean isSellingAll;
    @JsonProperty("isRating")
    private boolean isRating;

    private String sort;

    private String username;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getGoodsTypes() {
        return goodsTypes;
    }

    public void setGoodsTypes(List<String> goodsTypes) {
        this.goodsTypes = goodsTypes;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public boolean isRating() {
        return isRating;
    }

    public void setRating(boolean rating) {
        isRating = rating;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setSelling(boolean selling) {
        isSelling = selling;
    }

    public boolean isSellingAll() {
        return isSellingAll;
    }

    public void setSellingAll(boolean sellingAll) {
        isSellingAll = sellingAll;
    }


}
