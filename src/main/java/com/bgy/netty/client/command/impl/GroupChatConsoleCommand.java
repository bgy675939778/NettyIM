package com.bgy.netty.client.command.impl;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.protocol.request.GroupChatRequestPacket;
import com.bgy.netty.session.UserSession;
import com.bgy.netty.utils.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author bgy
 * @date 2020/1/16 21:38
 */
public class GroupChatConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【开始群聊】");
        System.out.println("请输入群组Id：");
        String groupId = scanner.next();
        System.out.println("请输入消息内容：");
        String message = scanner.next();

        UserSession userSession = SessionUtil.getSession(channel);

        GroupChatRequestPacket groupChatRequestPacket = new GroupChatRequestPacket(groupId, userSession.getUserId(), userSession.getUserName(), message);
        channel.writeAndFlush(groupChatRequestPacket);
    }
}
