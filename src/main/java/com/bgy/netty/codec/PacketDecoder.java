package com.bgy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author bgy
 * @date 2020/1/14 0:15
 */
public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {
    private PacketCodec packetCodec;

    public PacketDecoder() {
        packetCodec = new PacketCodec();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        out.add(packetCodec.decode(in));
    }
}
