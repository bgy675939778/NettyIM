package com.bgy.netty.protocol.request;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;

/**
 * @author bgy
 * @date 2020/1/21 23:05
 */
public class LogoutRequestPacket extends AbstractPacket {
    @Override
    public Byte getCommand() {
        return CommandsConstant.LOGOUT_REQUEST;
    }
}
