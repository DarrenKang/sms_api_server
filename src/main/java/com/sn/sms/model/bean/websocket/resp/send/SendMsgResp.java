package com.sn.sms.model.bean.websocket.resp.send;

import com.sn.sms.model.bean.websocket.resp.AbstractRespMsg;

/**
 * Created by jay on 11/14/16.
 */
public class SendMsgResp extends AbstractRespMsg {

    private String stat;
    private String tel;
    private String platformId;
    private boolean needWaiting=false;
    public boolean isNeedWaiting() {
		return needWaiting;
	}

	public void setNeedWaiting(boolean needWaiting) {
		this.needWaiting = needWaiting;
	}

	public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "SendMsgResp{" +
                "platformId='" + platformId + '\'' +
                ", stat='" + stat + '\'' +
                ", tel='" + tel + '\'' +
                ", needWaiting='" + needWaiting + '\'' +
                '}';
    }
}
