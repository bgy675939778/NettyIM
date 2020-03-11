package com.bgy.netty.client;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.client.command.ConsoleCommandFactory;
import com.bgy.netty.client.handler.*;
import com.bgy.netty.codec.PacketDecoder;
import com.bgy.netty.codec.PacketEncoder;
import com.bgy.netty.codec.Unpacker;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author bgy
 * @date 2020/1/12 23:33
 */
public class NettyClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                // 测试TCP链接的状态，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文；childOption
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 是否启动Nagle算法（将小的碎片数据连接成更大的报文来提高发送效率，
                // 虽然该方式有效提高网络的发送效率，但是却造成了延时），默认关闭；childOption
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline
                                .addLast(new LoggingHandler(LogLevel.INFO))
                                .addLast(new Unpacker())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new SingleChatResponseHandler())
                                .addLast(new CreateGroupResponseHandler())
                                .addLast(new GroupChatResponseHandler())
                                .addLast(new ListMembersResponseHandler())
                                .addLast(new JoinGroupResponseHandler())
                                .addLast(new QuitGroupResponseHandler())
                                .addLast(new LogoutResponseHandler())
                                .addLast(new PacketEncoder());
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    /**
     * @param bootstrap
     * @param host
     * @param port
     * @param residueTime 剩余重试次数
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int residueTime) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功，启动控制台线程接收输入：");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (residueTime <= 0) {
                System.err.println("重试次数已用完，放弃连接!");
            } else {
                //重连次数
                int retryTime = (MAX_RETRY - residueTime) + 1;
                //重试间隔
                int delay = 1;

                System.err.println("连接失败，第 " + retryTime + " 次重连!");
                bootstrap.config().group().schedule(() ->
                        connect(bootstrap, host, port, residueTime - 1), delay, TimeUnit.SECONDS);

            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        ConsoleCommandFactory consoleCommandFactory = new ConsoleCommandFactory();

        new Thread(() -> {
            //线程一直死循环执行接收控制台输入
            while (!Thread.interrupted()) {
                String command = scanner.next();
                if (consoleCommandFactory.map.containsKey(command)) {
                    ConsoleCommand consoleCommand = consoleCommandFactory.map.get(command);
                    consoleCommand.exec(scanner, channel);
                } else {
                    System.err.println("请输入正确的命令！");
                }
            }
        }).start();
    }
}
