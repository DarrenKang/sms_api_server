package com.sn.sms.model.sms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sms_nexmoResponse")
public class NexmoResponse implements Serializable{
	//主键messageId
	@Id
	@Column(name = "messageId", unique = true, nullable = false)
    private String messageId;
	//打给的号码
    @Column(name = "msisdn")
    private String msisdn;
    //显示是谁打来的
    @Column(name = "to1")
    private String to;
    //网络标号
    @Column(name = "network_code")
    private String network_code;
    @Column(name = "price")
    private String price;
  //返回状态
    @Column(name = "status")
    private String status;
  //When the DLR was recieved from the carrier in the following format YYMMDDHHMM
    @Column(name = "scts")
    private String scts;
    @Column(name = "err_code")
    private String err_code;
    @Column(name = "message_timestamp")
    private String message_timestamp;
    
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getNetwork_code() {
		return network_code;
	}
	public void setNetwork_code(String network_code) {
		this.network_code = network_code;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScts() {
		return scts;
	}
	public void setScts(String scts) {
		this.scts = scts;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	
	public String getMessage_timestamp() {
		return message_timestamp;
	}
	public void setMessage_timestamp(String message_timestamp) {
		this.message_timestamp = message_timestamp;
	}
	@Override
	public String toString() {
		return "NexmoResponse [messageId=" + messageId + ", msisdn=" + msisdn + ", to=" + to + ", network_code="
				+ network_code + ", price=" + price + ", status=" + status + ", scts=" + scts + ", err_code=" + err_code
				+ ", message_timestamp=" + message_timestamp + "]";
	}
	
    
}
