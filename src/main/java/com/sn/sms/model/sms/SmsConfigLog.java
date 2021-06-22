package com.sn.sms.model.sms;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "smsConfig_log")
@DynamicInsert
@DynamicUpdate
public class SmsConfigLog {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	//主键
	private Integer id;
	@Column(name = "operator")
	//操作人
	private String operator;
	@Column(name = "operName")
	//调用方法名称
	private String operName;
	@Column(name = "operParams")
	//调用方法参数
	private String operParams;
	@Column(name = "opeResult")
	//操作结果（成功或失败）
	private String opeResult;
	@Column(name = "resultMsg")
	//结果消息
	private String resultMsg;
	@Column(name = "operTime")
	//操作时间
	private Date operTime;
	@Column(name = "platformIds")
	//平台号
    private String platformIds;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getOperParams() {
		return operParams;
	}
	public void setOperParams(String operParams) {
		this.operParams = operParams;
	}
	public String getOpeResult() {
		return opeResult;
	}
	public void setOpeResult(String opeResult) {
		this.opeResult = opeResult;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getPlatformIds() {
		return platformIds;
	}
	public void setPlatformIds(String platformIds) {
		this.platformIds = platformIds;
	}
	@Override
	public String toString() {
		return "SmsConfigLog [id=" + id + ", operator=" + operator + ", operName=" + operName + ", operParams="
				+ operParams + ", opeResult=" + opeResult + ", resultMsg=" + resultMsg + ", operTime=" + operTime
				+ ", platformIds=" + platformIds + "]";
	}
	
	
}
