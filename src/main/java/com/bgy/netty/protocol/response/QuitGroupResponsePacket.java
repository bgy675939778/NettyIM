package com.bgy.netty.protocol.response;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import com.bgy.netty.session.UserSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bgy
 * @date 2020/1/21 22:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuitGroupResponsePacket extends AbstractPacket {
    private String groupId;
    private UserSession userSession;

    @Override
    public Byte getCommand() {
        return CommandsConstant.QUIT_GROUP_RESPONSE;
    }
}
