package com.sn.sms.service.interfaces;

import com.sn.sms.model.sms.SmsConfigLog;
import com.sn.sms.utils.Page;

import java.util.Date;

public interface SmsConfigLogService {
	public void saveEntity(SmsConfigLog smsConfigLog);
	public Page querySMSLogs(String name, String projectname, Date start, Date end, Integer pageIndex, Integer size);
}
