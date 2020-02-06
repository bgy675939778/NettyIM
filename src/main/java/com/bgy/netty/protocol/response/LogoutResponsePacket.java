package com.bgy.netty.protocol.response;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;

/**
 * @author bgy
 * @date 2020/1/21 23:09
 */
public class LogoutResponsePacket extends AbstractPacket {
    @Override
    public Byte getCommand() {
        return CommandsConstant.LOGOUT_RESPONSE;
    }
}
