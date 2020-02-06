package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.request.QuitGroupRequestPacket;
import com.bgy.netty.protocol.response.QuitGroupResponsePacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author bgy
 * @date 2020/1/21 22:50
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        UserSession userSession = SessionUtil.getSession(ctx.channel());
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket(groupId, userSession);
        channelGroup.writeAndFlush(quitGroupResponsePacket);
    }
}
