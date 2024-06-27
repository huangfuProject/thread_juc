package com.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.TimeUnit;

public class NettyServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                           .channel(NioServerSocketChannel.class)
                           .childHandler(new ChannelInitializer<SocketChannel>() {
                               @Override
                               protected void initChannel(SocketChannel ch) {
                                   ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                       @Override
                                       public void channelActive(ChannelHandlerContext ctx) {
                                           ctx.channel().eventLoop().scheduleAtFixedRate(() -> {
                                               // 在这里检查接收窗口是否为0
                                               Channel channel = ctx.channel();
                                               if (channel.config().isAutoRead() && channel.isWritable()) {
                                                   // 窗口不为0，可以继续发送数据
                                               } else {
                                                   System.out.println("为0了");
                                               }
                                               System.out.println("发送消息");
                                               ctx.writeAndFlush(Unpooled.wrappedBuffer(new byte[1024]));
                                           }, 0, 1, TimeUnit.MILLISECONDS);
                                       }
                                   });
                               }
                           });

            ChannelFuture future = serverBootstrap.bind(8888).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
