package com.sn.sms.service.impl;

import com.sn.sms.model.bean.websocket.RequestMessage;
import com.sn.sms.model.bean.websocket.ResponseMessage;
import com.sn.sms.service.interfaces.ChannelService;
import com.sn.sms.service.interfaces.HeartBeatService;
import com.sn.sms.service.interfaces.PlatformService;
import com.sn.sms.service.interfaces.SmsService;
import com.sn.sms.service.interfaces.UsableChannelService;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by jay on 11/16/16.
 */
@Component
public class WebSocketServiceProvider {

    @Autowired
    private SmsService smsService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private HeartBeatService heartBeatService;
    @Autowired
    private UsableChannelService usableChannelService;
    public ResponseMessage processReq(RequestMessage req) {
        switch (req.getService()) {
            case "sending" :
                return smsService.processWSReq(req);
            case "sendingGroupMessage":
            	return smsService.processGroupWSReq(req);
            case "platform" :
                return platformService.processReq(req);
            case "channel" :
                return channelService.processReq(req);
            case "heartBeat" :
                return heartBeatService.processReq(req);
            case "getUsableChannel" :
                return usableChannelService.processReq(req);
            default:
                return null;
        }
    }

}
