package com.bgy.netty.client.command;

import com.bgy.netty.client.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bgy
 * @date 2020/1/13 22:20
 * @desciption 工厂模式，生成具体控制台命令
 */
public class ConsoleCommandFactory {
    public Map<String, ConsoleCommand> map;

    public ConsoleCommandFactory() {
        map = new HashMap<>();
        map.put("Login", new LoginConsoleCommand());
        map.put("SingleChat", new SingleChatConsoleCommand());
        map.put("CreateGroup", new CreateGroupConsoleCommand());
        map.put("GroupChat", new GroupChatConsoleCommand());
        map.put("ListMembers", new ListMembersConsoleCommand());
        map.put("JoinGroup", new JoinGroupConsoleCommand());
        map.put("QuitGroup", new QuitGroupConsoleCommand());
        map.put("Logout", new LogoutConsoleCommand());
    }
}
