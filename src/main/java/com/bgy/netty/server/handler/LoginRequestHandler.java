package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.request.LoginRequestPacket;
import com.bgy.netty.protocol.response.LoginResponsePacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author bgy
 * @date 2020/1/13 23:08
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        if (validate(loginRequestPacket)) {
            loginResponsePacket.setUserId(UUID.randomUUID().toString().split("-")[0]);
            loginResponsePacket.setUserName(loginRequestPacket.getUserName());
            loginResponsePacket.setPassword(loginRequestPacket.getPassword());
            loginResponsePacket.setSuccess(true);

            System.out.println("[" + loginResponsePacket.getUserName() + "] 登录成功!");
            SessionUtil.bindSession(new UserSession(loginResponsePacket.getUserId(), loginResponsePacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码错误！");
            loginResponsePacket.setSuccess(false);


            System.out.println("[" + loginResponsePacket.getUserName() + "] 登录失败!");
        }

        ctx.writeAndFlush(loginResponsePacket);
    }

    private boolean validate(LoginRequestPacket loginRequestPacket) {
        //这里直接返回true，如果需要另外数据源进行验证的可扩展
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
        System.out.println("连接已断开！");
    }
}
