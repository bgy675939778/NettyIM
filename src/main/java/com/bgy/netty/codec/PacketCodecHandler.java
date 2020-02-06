package com.bgy.netty.codec;

import com.bgy.netty.protocol.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author bgy
 * @date 2020/1/13 23:35
 */
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, AbstractPacket> {
    private PacketCodec packetCodec;

    public PacketCodecHandler() {
        packetCodec = new PacketCodec();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket abstractPacket, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        packetCodec.encode(byteBuf, abstractPacket);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        out.add(packetCodec.decode(byteBuf));
    }
}
