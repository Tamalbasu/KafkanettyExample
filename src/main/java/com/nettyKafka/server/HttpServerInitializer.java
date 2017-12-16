package com.nettyKafka.server;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

import com.nettyKafka.utils.LogUtil;
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
	private static final LogUtil log = new LogUtil(HttpServerInitializer.class);
    private final SslContext sslCtx;

    public HttpServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        try {
			ChannelPipeline p = ch.pipeline();
			if (sslCtx != null) {
			    p.addLast(sslCtx.newHandler(ch.alloc()));
			}
			p.addLast(new HttpRequestDecoder());
     
			p.addLast(new HttpResponseEncoder());
    
			p.addLast(new HttpServerHandler());
		} catch (Exception e) {
			log.error(e);
		}
    }
}