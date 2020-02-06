package com.bgy.netty.codec;

import com.bgy.netty.constants.CommandsConstant;
import com.bgy.netty.protocol.AbstractPacket;
import com.bgy.netty.protocol.request.*;
import com.bgy.netty.protocol.response.*;
import com.bgy.netty.serialize.Serializer;
import com.bgy.netty.serialize.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;
import sun.security.krb5.internal.PAData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bgy
 * @date 2020/1/12 0:16
 * @desciption 数据包格式：
 * |魔数 |版本号|序列化算法|指令|数据长度|数据 |
 * |4字节|1字节 |  1字节  |1字节| 4字节 |n字节|
 */
public class PacketCodecUtil {
    private Serializer serializer;
    //数据包类型的map
    private final Map<Byte, Class<? extends AbstractPacket>> packetTypeMap;

    public PacketCodecUtil() {
        serializer = new JsonSerializer();

        packetTypeMap = new HashMap<>();
        packetTypeMap.put(CommandsConstant.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(CommandsConstant.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(CommandsConstant.SINGLE_MESSAGE_REQUEST, SingleChatRequestPacket.class);
        packetTypeMap.put(CommandsConstant.SINGLE_MESSAGE_RESPONSE, SingleChatResponsePacket.class);
        packetTypeMap.put(CommandsConstant.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(CommandsConstant.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(CommandsConstant.GROUP_MESSAGE_REQUEST, GroupChatRequestPacket.class);
        packetTypeMap.put(CommandsConstant.GROUP_MESSAGE_RESPONSE, GroupChatResponsePacket.class);
        packetTypeMap.put(CommandsConstant.LIST_GROUP_MEMBERS_REQUEST, ListMembersRequestPacket.class);
        packetTypeMap.put(CommandsConstant.LIST_GROUP_MEMBERS_RESPONSE, ListMembersResponsePacket.class);
        packetTypeMap.put(CommandsConstant.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(CommandsConstant.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(CommandsConstant.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(CommandsConstant.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(CommandsConstant.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(CommandsConstant.LOGOUT_RESPONSE, LogoutResponsePacket.class);
    }

    public void encode(ByteBuf byteBuf, AbstractPacket abstractPacket) {
        //序列化java对象
        byte[] bytes = serializer.serialize(abstractPacket);

        //实际编码过程
        byteBuf.writeInt(abstractPacket.getMagicNumber());
        byteBuf.writeByte(abstractPacket.getVersion());
        byteBuf.writeByte(abstractPacket.getSerializerAlgorithm());
        byteBuf.writeByte(abstractPacket.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public AbstractPacket decode(ByteBuf byteBuf) {
        //跳过magic number
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        //跳过序列化算法（因为目前只使用JSON序列化）
        byteBuf.skipBytes(1);

        //获取指令
        byte command = byteBuf.readByte();
        //获取数据包长度
        int length = byteBuf.readInt();
        //获取数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends AbstractPacket> clazz = packetTypeMap.get(command);
        if (clazz != null) {
            return serializer.deserialize(clazz, bytes);
        }
        return null;
    }
}
