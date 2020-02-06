package com.bgy.netty.client.handler;

import com.bgy.netty.protocol.response.QuitGroupResponsePacket;
import com.bgy.netty.session.UserSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bgy
 * @date 2020/1/21 22:55
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        String groupId = quitGroupResponsePacket.getGroupId();
        UserSession userSession = quitGroupResponsePacket.getUserSession();

        System.out.println("用户 " + userSession.getUserName() + "(" + userSession.getUserId() + ") 退出了群聊 [" + groupId + "] !");
    }
}
