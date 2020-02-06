package com.bgy.netty.client.handler;

import com.bgy.netty.protocol.response.ListMembersResponsePacket;
import com.bgy.netty.utils.Pair;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author bgy
 * @date 2020/1/17 0:00
 */
public class ListMembersResponseHandler extends SimpleChannelInboundHandler<ListMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListMembersResponsePacket listMembersResponsePacket) throws Exception {
        String groupId = listMembersResponsePacket.getGroupId();
        List<Pair<String, String>> userPairs = listMembersResponsePacket.getUserPair();

        System.out.println("群 [" + groupId + "] 的成员有：");
        userPairs.forEach(userPair -> {
            System.out.println(userPair.getV2() + "(" + userPair.getV1() + ")");
        });
    }
}
