package com.nettyKafka.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;

import com.nettyKafka.utils.LogUtil;

public class HttpServer {
	//static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port",  "9001"));
    private static final LogUtil log = new LogUtil(HttpServer.class);
		

	    public static void main(String[] args) throws Exception {
	 
	    
	       
	        final SslContext sslCtx=null;
	      
	        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class)
	             .handler(new LoggingHandler(LogLevel.INFO))
	             .childHandler(new HttpServerInitializer(sslCtx));
	            Channel ch = b.bind(PORT).sync().channel();
	            log.info("Server Started...");
	           log.info("Open your web browser and navigate to " +
	                  "http://127.0.0.1:" + PORT + '/');
	           
	            ch.closeFuture().sync();
	            
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	            log.info("Channel closed");
	        }
	    }

}
