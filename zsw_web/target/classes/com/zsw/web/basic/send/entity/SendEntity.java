package com.zsw.web.basic.send.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.send.entity
 * @date 2020/6/12 15:24
 */
public class SendEntity implements Serializable {

    private static final long serialVersionUID = -188779632062841696L;

    private String sendId;
    private String sendName;
    private int sendSex;
    private String sendPhone;
    private Date addTime;

    public SendEntity() {
    }

    public SendEntity(String sendId, String sendName, int sendSex, String sendPhone, Date addTime) {
        this.sendId = sendId;
        this.sendName = sendName;
        this.sendSex = sendSex;
        this.sendPhone = sendPhone;
        this.addTime = addTime;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public int getSendSex() {
        return sendSex;
    }

    public void setSendSex(int sendSex) {
        this.sendSex = sendSex;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
