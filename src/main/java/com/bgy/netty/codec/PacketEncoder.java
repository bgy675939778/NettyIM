package com.bgy.netty.codec;

import com.bgy.netty.protocol.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author bgy
 * @date 2020/1/14 0:18
 */
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {
    private PacketCodecUtil packetCodecUtil;

    public PacketEncoder() {
        packetCodecUtil = new PacketCodecUtil();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket abstractPacket, ByteBuf out) throws Exception {
        packetCodecUtil.encode(out, abstractPacket);
    }
}
