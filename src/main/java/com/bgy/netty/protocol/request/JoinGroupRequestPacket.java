package com.bgy.netty.protocol.request;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bgy
 * @date 2020/1/21 21:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinGroupRequestPacket extends AbstractPacket {
    private String groupId;

    @Override
    public Byte getCommand() {
        return CommandsConstant.JOIN_GROUP_REQUEST;
    }
}
