package com.sn.sms.service.interfaces;

import com.sn.sms.model.bean.websocket.RequestMessage;
import com.sn.sms.model.bean.websocket.ResponseMessage;
import com.sn.sms.model.sms.Platform;

import java.util.List;

/**
 * Created by jay on 11/16/16.
 */
public interface PlatformService {

    ResponseMessage processReq(RequestMessage req);

    List<Platform> getPlatformList(String platformId);
}
