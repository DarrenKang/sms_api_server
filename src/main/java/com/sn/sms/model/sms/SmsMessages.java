package com.sn.sms.model.sms;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tom on 10/25/16.
 */

@Entity
@Table(name = "sms_msg")
@DynamicInsert
@DynamicUpdate
public class SmsMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsms_msg")
    private Integer idsms_msg;
    @Column(name = "product")
    private String product;
    @Column(name = "smsprovider")
    private String smsprovider;
    @Column(name = "user_account")
    private String user_account;
    @Column(name = "phonenumber")
    private String phonenumber;
    @Column(name = "content")
    private String content;
    @Column(name = "result")
    private String result;
    @Column(name = "createtime")
    private Date createtime;

    public SmsMessages() {
    }

    public SmsMessages(String product, String smsprovider, String user_account, String phonenumber, String content, String result, Date createtime) {
        this.product = product;
        this.smsprovider = smsprovider;
        this.user_account = user_account;
        this.phonenumber = phonenumber;
        this.content = content;
        this.result = result;
        this.createtime = createtime;
    }

    public Integer getIdsms_msg() {
        return idsms_msg;
    }

    public void setIdsms_msg(Integer idsms_msg) {
        this.idsms_msg = idsms_msg;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSmsprovider() {
        return smsprovider;
    }

    public void setSmsprovider(String smsprovider) {
        this.smsprovider = smsprovider;
    }

    @Override
    public String toString() {
        return "SmsMessages{" +
                "idsms_msg=" + idsms_msg +
                ", product='" + product + '\'' +
                ", smsprovider='" + smsprovider + '\'' +
                ", user_account='" + user_account + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", content='" + content + '\'' +
                ", result='" + result + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
