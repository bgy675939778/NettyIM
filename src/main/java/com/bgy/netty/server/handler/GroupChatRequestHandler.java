package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.request.GroupChatRequestPacket;
import com.bgy.netty.protocol.response.GroupChatResponsePacket;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author bgy
 * @date 2020/1/16 21:44
 */
public class GroupChatRequestHandler extends SimpleChannelInboundHandler<GroupChatRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestPacket groupChatRequestPacket) throws Exception {
        String groupId = groupChatRequestPacket.getGroupId();
        String fromUserId = groupChatRequestPacket.getFromUserId();
        String fromUserName = groupChatRequestPacket.getFromUserName();
        String message = groupChatRequestPacket.getMessage();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        GroupChatResponsePacket groupChatResponsePacket = new GroupChatResponsePacket(fromUserId, fromUserName, message);

        channelGroup.writeAndFlush(groupChatResponsePacket);
    }
}
