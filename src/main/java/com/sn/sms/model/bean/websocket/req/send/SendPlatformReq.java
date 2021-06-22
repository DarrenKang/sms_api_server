package com.sn.sms.model.bean.websocket.req.send;

import com.sn.sms.model.bean.websocket.req.AbstractReqMsg;

public class SendPlatformReq  extends AbstractReqMsg{
	 private String platformId;

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	@Override
	public String toString() {
		return "SendPlatformReq [platformId=" + platformId + "]";
	}
	 
}
