package com.bgy.netty.client.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author bgy
 * @date 2020/1/13 22:18
 * @desciption 控制台命令接口
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
