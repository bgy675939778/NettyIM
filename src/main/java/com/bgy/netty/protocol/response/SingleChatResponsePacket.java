package com.bgy.netty.protocol.response;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bgy
 * @date 2020/1/15 0:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleChatResponsePacket extends AbstractPacket {
    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return CommandsConstant.SINGLE_MESSAGE_RESPONSE;
    }
}
