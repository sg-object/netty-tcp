package com.sg.netty.tcp.server.enums;

public enum HeaderKey {

	TOTAL(0, 200), BYTE_SIZE(0, 7), ENCODING(121, 122);

	private HeaderKey(int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	private final int startIndex;

	private final int endIndex;

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}
}
