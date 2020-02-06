package com.bgy.netty.protocol.request;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bgy
 * @date 2020/1/21 22:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuitGroupRequestPacket extends AbstractPacket {
    private String groupId;

    @Override
    public Byte getCommand() {
        return CommandsConstant.QUIT_GROUP_REQUEST;
    }
}
