package com.bgy.netty.server;

import com.bgy.netty.codec.PacketCodec;
import com.bgy.netty.codec.Unpacker;
import com.bgy.netty.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author bgy
 * @date 2020/1/12 23:18
 */
public class NettyServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                // 大量连接排队在queue里面，queue的大小；对应于option
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 是否启动Nagle算法（将小的碎片数据连接成更大的报文来提高发送效率，虽然该方式有效提高网络的发送效率，但是却造成了延时），
                // 默认关闭；option和childOption都有
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline
                                .addLast(new LoggingHandler(LogLevel.INFO))
                                .addLast(new Unpacker())
                                .addLast(new PacketCodec())
                                .addLast(new LoginRequestHandler())
                                .addLast(new SingleChatRequestHandler())
                                .addLast(new CreateGroupRequestHandler())
                                .addLast(new GroupChatRequestHandler())
                                .addLast(new ListMembersRequestHandler())
                                .addLast(new JoinGroupRequestHandler())
                                .addLast(new QuitGroupRequestHandler())
                                .addLast(new LogoutRequestHandler());
                    }
                });

        serverBootstrap.bind(PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口 [" + PORT + "] 绑定成功!");
            } else {
                System.out.println("端口 [" + PORT + "] 绑定失败!");
            }
        });
    }
}
