package com.sg.netty.tcp.server;

import java.net.InetSocketAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.sg.netty.tcp.server.decoder.MessageDecoder;
import com.sg.netty.tcp.server.handler.MessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

public class TcpServer {

	private final Logger logger = LogManager.getLogger(this.getClass());

	private final String HOST = "0.0.0.0";

	private final int PORT = 9999;

	private final int READ_TIME_OUT = 10;

	private final int WRITE_TIME_OUT = 10;

	public static void main(String[] args) {
		TcpServer server = new TcpServer();
		server.start();
	}

	private void start() {
		final EventLoopGroup group = new NioEventLoopGroup();
		final ServerBootstrap serverBootstrap = new ServerBootstrap();
		final MessageHandler messageHandler = new MessageHandler();

		serverBootstrap.group(group);
		serverBootstrap.channel(NioServerSocketChannel.class);
		serverBootstrap.localAddress(new InetSocketAddress(HOST, PORT));
		serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				
				/*socketChannel.pipeline().addLast("ReadTimeoutHandler", new ReadTimeoutHandler(READ_TIME_OUT));
				socketChannel.pipeline().addLast("WriteTimeoutHandler", new WriteTimeoutHandler(WRITE_TIME_OUT));*/

				socketChannel.pipeline().addLast(new MessageDecoder());
				socketChannel.pipeline().addLast(new UnorderedThreadPoolEventExecutor(10), messageHandler);
			}
		}).option(ChannelOption.SO_BACKLOG, 10).childOption(ChannelOption.SO_KEEPALIVE, true);
		try {
			// Start the server.
			final ChannelFuture channelFuture = serverBootstrap.bind().sync();
			// Start the server.
			logger.info("Start TCP Server");
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				// Shut down all event loops to terminate all threads.
				group.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
