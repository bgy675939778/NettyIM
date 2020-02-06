package com.bgy.netty.client.command.impl;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.protocol.request.ListMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author bgy
 * @date 2020/1/16 23:31
 */
public class ListMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【成员列表】");
        System.out.println("请输入群组Id：");
        String groupId = scanner.next();

        ListMembersRequestPacket listMembersRequestPacket = new ListMembersRequestPacket(groupId);

        channel.writeAndFlush(listMembersRequestPacket);
    }
}
