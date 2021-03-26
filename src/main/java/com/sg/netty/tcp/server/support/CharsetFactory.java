package com.sg.netty.tcp.server.support;

import java.nio.charset.Charset;
import com.sg.netty.tcp.server.enums.CharsetType;

public class CharsetFactory {

	public static Charset createCharset(CharsetType type) {
		return Charset.forName(type.getType());
	}
}
