package com.bgy.netty.client.command.impl;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;
import org.springframework.util.StringUtils;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author bgy
 * @date 2020/1/13 22:23
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【用户登录】");
        System.out.println("请输入用户名：");
        String userName = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();

        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            LoginRequestPacket loginRequestPacket = new LoginRequestPacket(userName, password);
            //发送登录请求数据包
            channel.writeAndFlush(loginRequestPacket);

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {

            }
        } else {
            System.out.println("请输入用户名和密码！");
        }
    }
}
