package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.request.JoinGroupRequestPacket;
import com.bgy.netty.protocol.response.JoinGroupResponsePacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author bgy
 * @date 2020/1/21 22:02
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        UserSession userSession = SessionUtil.getSession(ctx.channel());
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket(groupId, userSession);

        channelGroup.writeAndFlush(joinGroupResponsePacket);
    }
}
