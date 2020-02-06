package com.bgy.netty.utils;

import com.bgy.netty.constants.AttributesConstant;
import com.bgy.netty.session.UserSession;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bgy
 * @date 2020/1/14 22:46
 */
public class SessionUtil {
    //保存用户和channel的map
    private static Map<String, Channel> user2ChannelMap = new HashMap<>();
    //保存群组和channelGroup的map
    private static Map<String, ChannelGroup> group2ChannelGroupMap = new HashMap<>();

    public static void bindSession(UserSession session, Channel channel) {
        user2ChannelMap.put(session.getUserId(), channel);
        channel.attr(AttributesConstant.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            UserSession session = getSession(channel);
            user2ChannelMap.remove(session.getUserId());
            channel.attr(AttributesConstant.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static UserSession getSession(Channel channel) {
        return channel.attr(AttributesConstant.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return user2ChannelMap.get(userId);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        group2ChannelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return group2ChannelGroupMap.get(groupId);
    }
}
