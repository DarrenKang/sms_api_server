package com.sn.sms.service.interfaces;

import com.sn.sms.model.bean.websocket.RequestMessage;
import com.sn.sms.model.bean.websocket.ResponseMessage;

public interface HeartBeatService {
	ResponseMessage processReq(RequestMessage req);
}
