package com.bgy.netty.client.command.impl;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author bgy
 * @date 2020/1/15 23:15
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【创建群组】");
        System.out.println("请输入群组名称:");
        String groupName = scanner.next();
        System.out.println("请输入群组成员userId（以\",\"分割）");
        String userIds = scanner.next();

        List<String> userIdList = Arrays.asList(userIds.split(","));
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket(groupName, userIdList);

        channel.writeAndFlush(createGroupRequestPacket);
    }
}
