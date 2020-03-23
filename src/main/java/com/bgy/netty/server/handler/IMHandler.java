package com.bgy.netty.server.handler;

import com.bgy.netty.protocol.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static com.bgy.netty.constants.CommandsConstant.*;

/**
 * @author bgy
 * @date 2020/2/6 14:36
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<AbstractPacket> {
    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends AbstractPacket>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(SINGLE_MESSAGE_REQUEST, SingleChatRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupChatRequestHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_MEMBERS_REQUEST, ListMembersRequestHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket abstractPacket) throws Exception {
        handlerMap.get(abstractPacket.getCommand()).channelRead(ctx, abstractPacket);
    }
}
