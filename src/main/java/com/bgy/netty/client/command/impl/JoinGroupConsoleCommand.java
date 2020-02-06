package com.bgy.netty.client.command.impl;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author bgy
 * @date 2020/1/21 21:49
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【加入群组】");
        System.out.println("请输入群组Id：");
        String groupId = scanner.next();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
