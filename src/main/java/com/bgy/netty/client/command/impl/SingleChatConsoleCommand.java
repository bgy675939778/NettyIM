package com.bgy.netty.client.command.impl;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.protocol.request.SingleChatRequestPacket;
import io.netty.channel.Channel;
import org.springframework.util.StringUtils;

import java.util.Scanner;

/**
 * @author bgy
 * @date 2020/1/14 23:38
 */
public class SingleChatConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【开始单聊】");
        System.out.println("请输入聊天对象ID：");
        String toUserId = scanner.next();
        System.out.println("请输入聊天内容：");
        String message = scanner.next();

        if (!StringUtils.isEmpty(toUserId) && !StringUtils.isEmpty(message)) {
            SingleChatRequestPacket singleChatRequestPacket = new SingleChatRequestPacket(toUserId, message);
            channel.writeAndFlush(singleChatRequestPacket);
        } else {
            System.out.println("请输入聊天对象ID和内容！");
        }
    }
}
