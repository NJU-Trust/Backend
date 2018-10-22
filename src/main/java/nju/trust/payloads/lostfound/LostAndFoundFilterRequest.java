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
    private List<ThingsType> thingsTypes;

    @JsonProperty("lostPlaces")
    private List<LostPlace> lostPlaces;

    @JsonProperty("msgProperties")
    private List<MsgProperty> msgProperties;

    @NotNull
    private int size;

    @NotNull
    private int page;

    @NotNull
    private String properties;

    @NotNull
    private String sort;

    public List<ThingsType> getThingsTypes() {
        return thingsTypes;
    }

    public void setThingsTypes(List<ThingsType> thingsTypes) {
        this.thingsTypes = thingsTypes;
    }

    public List<LostPlace> getLostPlaces() {
        return lostPlaces;
    }

    public void setLostPlaces(List<LostPlace> lostPlaces) {
        this.lostPlaces = lostPlaces;
    }

    public List<MsgProperty> getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(List<MsgProperty> msgProperties) {
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
