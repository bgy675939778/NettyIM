package com.bgy.netty.protocol.response;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.Data;

/**
 * @author bgy
 * @date 2020/1/13 23:05
 */
@Data
public class LoginResponsePacket extends AbstractPacket {
    private String userId;
    private String userName;
    private String password;

    private boolean success;
    private String reason;


    @Override
    public Byte getCommand() {
        return CommandsConstant.LOGIN_RESPONSE;
    }
}
