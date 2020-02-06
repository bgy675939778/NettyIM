package com.bgy.netty.client.command.impl;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author bgy
 * @date 2020/1/21 23:02
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【退出登录】");
        System.out.println("确认退出登录？");
        String answer = scanner.next();

        if ("yes".equals(answer)) {
            channel.writeAndFlush(new LogoutRequestPacket());
        }
    }
}
