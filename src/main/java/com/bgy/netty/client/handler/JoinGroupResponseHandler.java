package com.bgy.netty.client.handler;

import com.bgy.netty.protocol.response.JoinGroupResponsePacket;
import com.bgy.netty.session.UserSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bgy
 * @date 2020/1/21 22:09
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        UserSession userSession = joinGroupResponsePacket.getUserSession();
        System.out.println("用户 " + userSession.getUserName() + "(" + userSession.getUserId() + ") 加入了群聊 [" + joinGroupResponsePacket.getGroupId() + "] !");
    }
}
