package com.sn.sms.model.sms;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "smsAds")
@DynamicInsert
@DynamicUpdate
public class SmsAds implements Serializable{
	@Id
    @Column(name = "channel", unique = true, nullable = false)
	    private Integer channel;
	 @Column(name = "ads")
	    private String ads;
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public String getAds() {
		return ads;
	}
	public void setAds(String ads) {
		this.ads = ads;
	}
	@Override
	public String toString() {
		return "SmsAds [channel=" + channel + ", ads=" + ads + "]";
	}
	 
}
