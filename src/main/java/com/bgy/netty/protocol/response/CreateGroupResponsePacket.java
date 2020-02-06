package com.bgy.netty.protocol.response;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bgy
 * @date 2020/1/15 23:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupResponsePacket extends AbstractPacket {
    private String groupId;
    private String groupName;
    private List<String> userNames;
    private boolean success;

    @Override
    public Byte getCommand() {
        return CommandsConstant.CREATE_GROUP_RESPONSE;
    }
}
