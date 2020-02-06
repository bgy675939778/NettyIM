package com.bgy.netty.protocol.request;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author bgy
 * @date 2020/1/13 0:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestPacket extends AbstractPacket {
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return CommandsConstant.LOGIN_REQUEST;
    }
}
