package com.bgy.netty.client.handler;

import com.bgy.netty.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bgy
 * @date 2020/1/15 23:31
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println("你已被邀请加入群组： " + createGroupResponsePacket.getGroupName() + "(" + createGroupResponsePacket.getGroupId() + ")");
        System.out.println("群组里面有：" + createGroupResponsePacket.getUserNames());
    }
}
