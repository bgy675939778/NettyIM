package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.request.LogoutRequestPacket;
import com.bgy.netty.protocol.response.LogoutResponsePacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bgy
 * @date 2020/1/21 23:07
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutRequestPacket) throws Exception {
        Channel channel = ctx.channel();
        SessionUtil.unBindSession(channel);
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        ctx.writeAndFlush(logoutResponsePacket);
    }
}
