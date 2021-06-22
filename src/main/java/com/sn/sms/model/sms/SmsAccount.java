package com.sn.sms.model.sms;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jay on 7/12/16.
 *
 * product type of FPMS (i.e. RUIBO, BAIBO, etc...)
 *
 */
@Entity
@Table(name = "smsAccount")
public class SmsAccount implements Serializable {

    @Id
    @Column(name = "loginname", unique = true, nullable = false)
    private String loginname;
    @Column(name = "password")
    private String password;
    @Column(name = "platformIds")
    private String platformIds;


    public SmsAccount() { }

    public SmsAccount(String loginname, String platformIds, String password) {
        this.loginname = loginname;
        this.platformIds = platformIds;
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatformIds() {
        return platformIds;
    }

    public void setPlatformIds(String platformIds) {
        this.platformIds = platformIds;
    }

    @Override
    public String toString() {
        return "SmsAccount{" +
                "loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", platformIds='" + platformIds + '\'' +
                '}';
    }
}
