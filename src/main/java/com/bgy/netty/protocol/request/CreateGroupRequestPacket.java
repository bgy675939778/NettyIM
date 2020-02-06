package com.bgy.netty.protocol.request;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bgy
 * @date 2020/1/15 23:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupRequestPacket extends AbstractPacket {
    private String groupName;
    private List<String> userIds;

    @Override
    public Byte getCommand() {
        return CommandsConstant.CREATE_GROUP_REQUEST;
    }
}
