package com.bgy.netty.protocol.request;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bgy
 * @date 2020/1/14 23:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleChatRequestPacket extends AbstractPacket {
    private String toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return CommandsConstant.SINGLE_MESSAGE_REQUEST;
    }
}
