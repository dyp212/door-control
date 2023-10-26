package com.door.control.sdk;

import com.door.control.sdk.handle.DeviceServerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@Component
public class DeviceServer {
	
	Logger logger = LoggerFactory.getLogger(DeviceServer.class);
	@Value("${boss.thread.count:2}")
	private int bossCount;

	@Value("${worker.thread.count:8}")
	private int workerCount;

	@Value("${websocket.port}")
	private int serverPort;

	@Autowired
    DeviceServerInitializer deviceServerInitializer;
	
	@Autowired
	ChannelGroup channelGroup;
	
	
	Channel channel;

	public ChannelFuture start() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(deviceServerInitializer);
			channel = b.bind(serverPort).sync().channel();
			return channel.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public void stop() throws Exception {
		if(channel != null) {
			channel.close();
		}
		channelGroup.close();
	}
}
