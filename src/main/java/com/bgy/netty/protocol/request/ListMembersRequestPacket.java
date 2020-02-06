package com.bgy.netty.protocol.request;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bgy
 * @date 2020/1/16 23:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListMembersRequestPacket extends AbstractPacket {
    private String groupId;

    @Override
    public Byte getCommand() {
        return CommandsConstant.LIST_GROUP_MEMBERS_REQUEST;
    }
}
