package com.sn.sms.service.impl;

import com.sn.sms.dao.SmsDao;
import com.sn.sms.model.sms.SmsConfigLog;
import com.sn.sms.service.interfaces.SmsConfigLogService;
import com.sn.sms.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("smsConfigLogService")
public class SmsConfigLogServiceImpl implements SmsConfigLogService {
	@Resource
	private SmsDao smsDao;
	@Override
	public void saveEntity(SmsConfigLog smsConfigLog) {
		// TODO Auto-generated method stub
		smsDao.saveEntity(smsConfigLog);
	}
	@Override
	public Page querySMSLogs(String operator, String platformIds, Date start, Date end, Integer pageIndex, Integer size) {
		// TODO Auto-generated method stub
		return smsDao.querySMSLogsContents(operator,platformIds,start,end,pageIndex,size);
	}

}
