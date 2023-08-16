package com.techelevator.model;

import java.util.ArrayList;

public class ImgUrl {
    private int id;
    private String imgUrl;
    private int potholeId;

    public ImgUrl() {
    }

    public ImgUrl(int id, String imgUrl, int potholeId) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.potholeId = potholeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPotholeId() {
        return potholeId;
    }

    public void setPotholeId(int potholeId) {
        this.potholeId = potholeId;
    }
}
