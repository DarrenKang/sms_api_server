package com.sn.sms.service.interfaces;

import com.sn.sms.model.bean.websocket.RequestMessage;
import com.sn.sms.model.bean.websocket.ResponseMessage;

/**
 * Created by jay on 11/17/16.
 */
public interface ChannelService {

    ResponseMessage processReq(RequestMessage req);
}
