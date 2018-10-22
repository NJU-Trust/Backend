package nju.trust.payloads.lostfound;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/22
 * @Todo:
 */
public class LostAndFoundFilterRequest {
    @JsonProperty("thingsTypes")
    private List<String> thingsTypes;

    @JsonProperty("lostPlaces")
    private List<String> lostPlaces;

    @JsonProperty("msgProperties")
    private List<String> msgProperties;

    @NotNull
    private int size;

    @NotNull
    private int page;

    @NotNull
    private String properties;

    @NotNull
    private String sort;


    public List<String> getThingsTypes() {
        return thingsTypes;
    }

    public void setThingsTypes(List<String> thingsTypes) {
        this.thingsTypes = thingsTypes;
    }

    public List<String> getLostPlaces() {
        return lostPlaces;
    }

    public void setLostPlaces(List<String> lostPlaces) {
        this.lostPlaces = lostPlaces;
    }

    public List<String> getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(List<String> msgProperties) {
        this.msgProperties = msgProperties;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
