package com.sn.sms.model.bean.websocket.req.platform;

import com.sn.sms.model.bean.websocket.req.AbstractReqMsg;
import com.sn.sms.validator.annotation.JsonRequired;

import java.util.List;

/**
 * Created by jay on 7/18/16.
 */
public class SyncDataReq extends AbstractReqMsg {

    public static final String NAME = "syncData";

    @JsonRequired
    private List<Platform> platforms;


    public List<Platform> getPlatforms() {
        return platforms;
    }

    public class Platform extends AbstractPlatformReqMsg {

        @Override
        public String toString() {
            return "Platform{" +
                    "platformId='" + platformId + '\'' +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SyncDataReq{" +
                "platforms=" + platforms +
                '}';
    }
}
