package com.bgy.netty.client.handler;

import com.bgy.netty.protocol.response.LoginResponsePacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bgy
 * @date 2020/1/13 23:24
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + userName + "] 登录成功，userId为: " + userId);
            SessionUtil.bindSession(new UserSession(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "] 登录失败，原因为：" + loginResponsePacket.getReason());
        }
    }
}
