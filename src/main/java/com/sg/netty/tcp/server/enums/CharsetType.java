package com.sg.netty.tcp.server.enums;

public enum CharsetType {

	ASCII("US-ASCII"), UTF8("UTF-8"), EUC_KR("EUC-KR");

	private final String type;

	private CharsetType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
