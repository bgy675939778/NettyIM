package com.bgy.netty.protocol.response;

import com.bgy.netty.client.command.ConsoleCommand;
import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import com.bgy.netty.utils.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bgy
 * @date 2020/1/16 23:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListMembersResponsePacket extends AbstractPacket {
    private String groupId;
    private List<Pair<String, String>> userPair;

    @Override
    public Byte getCommand() {
        return CommandsConstant.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
