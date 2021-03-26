package com.sg.netty.tcp.server.handler;

import java.nio.charset.Charset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sg.netty.tcp.server.enums.CharsetType;
import com.sg.netty.tcp.server.support.CharsetFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MessageHandler extends ChannelInboundHandlerAdapter {

	private final Logger logger = LogManager.getLogger(this.getClass());

	private final Charset ASCII = CharsetFactory.createCharset(CharsetType.ASCII);

	private final Charset UTF8 = CharsetFactory.createCharset(CharsetType.UTF8);

	private final Charset EUC_KR = CharsetFactory.createCharset(CharsetType.EUC_KR);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf inBuffer = (ByteBuf) msg;
		logger.info(inBuffer.toString(UTF8));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
	}
}
