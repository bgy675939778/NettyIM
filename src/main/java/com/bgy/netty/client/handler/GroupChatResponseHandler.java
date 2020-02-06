package com.bgy.netty.client.handler;

import com.bgy.netty.protocol.response.GroupChatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bgy
 * @date 2020/1/16 21:50
 */
public class GroupChatResponseHandler extends SimpleChannelInboundHandler<GroupChatResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatResponsePacket groupChatResponsePacket) throws Exception {
        String fromUserId = groupChatResponsePacket.getFromUserId();
        String fromUserName = groupChatResponsePacket.getFromUserName();
        String message = groupChatResponsePacket.getMessage();

        System.out.println("【群聊通知】");
        System.out.println(fromUserName + "(" + fromUserId + ") 说：" + message);
    }
}
