package com.bgy.netty.client.handler;

import com.bgy.netty.protocol.response.SingleChatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author bgy
 * @date 2020/1/15 0:22
 */
public class SingleChatResponseHandler extends SimpleChannelInboundHandler<SingleChatResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SingleChatResponsePacket singleChatResponsePacket) throws Exception {
        String message = singleChatResponsePacket.getMessage();
        if (singleChatResponsePacket.getFromUserId() != null) {
            String fromUserId = singleChatResponsePacket.getFromUserId();
            String fromUserName = singleChatResponsePacket.getFromUserName();
            System.out.println(fromUserName + "(" + fromUserId + ") 对你说: " + message);
        } else {
            System.err.println(message);
        }


    }
}
