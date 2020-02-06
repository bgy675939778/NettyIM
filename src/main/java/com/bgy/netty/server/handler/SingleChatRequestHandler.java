package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.request.SingleChatRequestPacket;
import com.bgy.netty.protocol.response.SingleChatResponsePacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bgy
 * @date 2020/1/15 0:06
 */
@ChannelHandler.Sharable
public class SingleChatRequestHandler extends SimpleChannelInboundHandler<SingleChatRequestPacket> {
    public static final SingleChatRequestHandler INSTANCE = new SingleChatRequestHandler();

    private SingleChatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleChatRequestPacket singleChatRequestPacket) throws Exception {
        //发送方的session
        UserSession fromUserSession = SessionUtil.getSession(ctx.channel());
        Channel fromChannel = ctx.channel();

        //发消息到接收方
        SingleChatResponsePacket toSingleChatResponsePacket = new SingleChatResponsePacket(fromUserSession.getUserId(),
                fromUserSession.getUserName(), singleChatRequestPacket.getMessage());
        //接收方的userId
        String toUserId = singleChatRequestPacket.getToUserId();
        //接收方的channel
        Channel channel = SessionUtil.getChannel(toUserId);
        if (channel != null && SessionUtil.hasLogin(channel)) {
            channel.writeAndFlush(toSingleChatResponsePacket).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("消息发送成功！");
                } else {
                    System.err.println("消息发送失败！");
                }
            });
        } else {
            System.err.println("[ " + toUserId + " ] 不在线，发送失败！");
            SingleChatResponsePacket singleChatResponsePacket = new SingleChatResponsePacket();
            singleChatResponsePacket.setMessage("[ " + toUserId + " ] 不在线，发送失败！");
            fromChannel.writeAndFlush(singleChatResponsePacket);
        }
    }
}
