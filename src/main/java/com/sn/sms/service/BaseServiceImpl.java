package com.sn.sms.service;

import com.sn.sms.model.bean.websocket.RequestMessage;
import com.sn.sms.model.bean.websocket.ResponseMessage;
import com.sn.sms.model.bean.websocket.resp.AbstractRespMsg;

/**
 * Created by kierpagdato on 7/21/16.
 */
public class BaseServiceImpl {

    public ResponseMessage createResponse(RequestMessage req, AbstractRespMsg resp){
        return new ResponseMessage(req.getService(), req.getFunctionName(), resp);
    }
}
