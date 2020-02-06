package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.request.CreateGroupRequestPacket;
import com.bgy.netty.protocol.response.CreateGroupResponsePacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author bgy
 * @date 2020/1/15 23:26
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIds = createGroupRequestPacket.getUserIds();
        List<String> userNames = new ArrayList<>();

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        userIds.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);

                UserSession userSession = SessionUtil.getSession(channel);
                userNames.add(userSession.getUserName());
            } else {
                System.err.println(userId + " 用户不存在");
            }
        });

        String groupId = UUID.randomUUID().toString().split("-")[0];
        String groupName = createGroupRequestPacket.getGroupName();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket(groupId, groupName, userNames, true);

        //使用channelGroup给各个客户端发送消息
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println("群组创建成功，名称为：" + groupName + "(" + groupId + ")");
        System.out.println("群组里面有：" + createGroupResponsePacket.getUserNames());

        //保存群组id和channelGroup
        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
