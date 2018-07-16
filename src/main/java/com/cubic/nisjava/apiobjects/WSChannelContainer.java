package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSChannelContainer {

	@SerializedName("channels")
	@Expose
	private List<WSChannel> channels = null;

	public List<WSChannel> getChannels() {
		return channels;
	}

	public void setChannels(List<WSChannel> channels) {
		this.channels = channels;
	}
}