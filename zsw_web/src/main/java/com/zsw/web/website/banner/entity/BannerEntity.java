package com.zsw.web.website.banner.entity;

import java.io.Serializable;
import java.util.Date;

public class BannerEntity implements Serializable {

    private static final long serialVersionUID = 5963282116089675383L;
    private String bannerId;
    private String bannerTitle;
    private String bannerIcon;
    private String bannerUrl;
    private int bannerFlag;
    private Date addTime;
    private int bannerType;

    private String img;

    @Override
    public String toString() {
        return "BannerEntity{" +
                "bannerId='" + bannerId + '\'' +
                ", bannerTitle='" + bannerTitle + '\'' +
                ", bannerIcon='" + bannerIcon + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", bannerFlag=" + bannerFlag +
                ", addTime=" + addTime +
                ", bannerType=" + bannerType +
                ", img='" + img + '\'' +
                '}';
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerIcon() {
        return bannerIcon;
    }

    public void setBannerIcon(String bannerIcon) {
        this.bannerIcon = bannerIcon;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public int getBannerFlag() {
        return bannerFlag;
    }

    public void setBannerFlag(int bannerFlag) {
        this.bannerFlag = bannerFlag;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getBannerType() {
        return bannerType;
    }

    public void setBannerType(int bannerType) {
        this.bannerType = bannerType;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
