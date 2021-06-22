package com.sn.sms.service.impl;

import com.sn.sms.model.bean.websocket.RequestMessage;
import com.sn.sms.model.bean.websocket.ResponseMessage;
import com.sn.sms.model.bean.websocket.req.heartBeat.HeartBeatReq;
import com.sn.sms.model.bean.websocket.resp.AbstractRespMsg;
import com.sn.sms.service.BaseServiceImpl;
import com.sn.sms.service.interfaces.HeartBeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service("heartBeatService")
public class HeartBeatServiceImpl extends BaseServiceImpl implements HeartBeatService {
	private static final Logger log = LoggerFactory.getLogger(HeartBeatServiceImpl.class);
	@Override
	public ResponseMessage processReq(RequestMessage req) {
		// TODO Auto-generated method stub
		switch (req.getFunctionName()) {
        case HeartBeatReq.NAME:
            return getResponseMessage(req);
        default :
            return null;
    }
	}
	public ResponseMessage getResponseMessage(RequestMessage req) {
		log.info("心跳代码执行");
		ResponseMessage responseMessage=new ResponseMessage();
		responseMessage.setService(req.getService());
		responseMessage.setFunctionName(req.getFunctionName());
		AbstractRespMsg abr=new AbstractRespMsg();
		abr.setStatus(200);
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		long timeInMillis = calendar.getTimeInMillis();
		abr.setErrorMsg("currentTime:"+String.valueOf(timeInMillis));
		responseMessage.setData(abr);
		return responseMessage;
	}

}
