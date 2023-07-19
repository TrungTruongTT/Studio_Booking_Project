package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OptSms {
    @SerializedName("otpId")
    private String otpId;
    @SerializedName("createAt")
    private Date createAt;
    @SerializedName("expiredAt")
    private Date expiredAt;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("otpValue")
    private String optValue;

    public OptSms(String otpId, Date createAt, Date expiredAt, String phoneNumber, String optValue) {
        this.otpId = otpId;
        this.createAt = createAt;
        this.expiredAt = expiredAt;
        this.phoneNumber = phoneNumber;
        this.optValue = optValue;
    }

    public OptSms(String otpId, String phoneNumber, String optValue) {
        this.otpId = otpId;
        this.phoneNumber = phoneNumber;
        this.optValue = optValue;
    }

    public OptSms(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtpId() {
        return otpId;
    }

    public void setOtpId(String otpId) {
        this.otpId = otpId;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
