package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.request.ListMembersRequestPacket;
import com.bgy.netty.protocol.response.ListMembersResponsePacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.Pair;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bgy
 * @date 2020/1/16 23:35
 */
public class ListMembersRequestHandler extends SimpleChannelInboundHandler<ListMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListMembersRequestPacket listMembersRequestPacket) throws Exception {
        String groupId = listMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        List<Channel> channels = new ArrayList<>();
        channelGroup.forEach(channel -> {
            channels.add(channel);
        });

        List<Pair<String, String>> userPairs = new ArrayList<>();
        channels.forEach(channel -> {
            UserSession userSession = SessionUtil.getSession(channel);
            userPairs.add(new Pair<>(userSession.getUserId(), userSession.getUserName()));
        });

        ListMembersResponsePacket listMembersResponsePacket = new ListMembersResponsePacket(groupId, userPairs);
        ctx.writeAndFlush(listMembersResponsePacket);

    }
}
