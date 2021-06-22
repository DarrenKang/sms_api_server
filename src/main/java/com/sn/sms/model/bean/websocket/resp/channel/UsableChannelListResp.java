package com.sn.sms.model.bean.websocket.resp.channel;

import java.util.List;

import com.sn.sms.model.bean.websocket.resp.AbstractRespMsg;

public class UsableChannelListResp extends AbstractRespMsg{
	 private List<Integer> channels;

	public List<Integer> getChannels() {
		return channels;
	}

	public void setChannels(List<Integer> channels) {
		this.channels = channels;
	}

	@Override
	public String toString() {
		return "UsableChannelListResp [channels=" + channels + "]";
	}
	 
}
