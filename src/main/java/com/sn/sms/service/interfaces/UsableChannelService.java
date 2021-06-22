package com.sn.sms.service.interfaces;

import com.sn.sms.model.bean.websocket.RequestMessage;
import com.sn.sms.model.bean.websocket.ResponseMessage;

public interface UsableChannelService {
	ResponseMessage processReq(RequestMessage req);
}
