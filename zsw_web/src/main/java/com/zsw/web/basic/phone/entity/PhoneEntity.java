package com.zsw.web.basic.phone.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.phone.entity
 * @date 2020/6/16 16:35
 */
public class PhoneEntity implements Serializable {

    private static final long serialVersionUID = -188779632062841696L;
    private String phoneId;
    private String phoneNum;
    private Date addTime;

    public PhoneEntity(String phoneId, String phoneNum, Date addTime) {
        this.phoneId = phoneId;
        this.phoneNum = phoneNum;
        this.addTime = addTime;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}

