package com.sn.sms.model.bean.websocket.resp.platform;


import com.sn.sms.model.bean.websocket.resp.AbstractRespMsg;

/**
 * Created by jay on 7/18/16.
 */
public class SyncDataResp extends AbstractRespMsg {

    public SyncDataResp(Integer status, String errorMsg){
        setStatus(status);
        setErrorMsg(errorMsg);
    }

    public SyncDataResp(){}

    @Override
    public String toString() {
        return "SyncDataResp{status='" + getStatus() + '\'' +
                ", errorMsg='" + getErrorMsg() + '\'' +
                '}';
    }
}
