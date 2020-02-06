package com.bgy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author bgy
 * @date 2020/1/14 0:15
 */
public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {
    private PacketCodecUtil packetCodecUtil;

    public PacketDecoder() {
        packetCodecUtil = new PacketCodecUtil();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        out.add(packetCodecUtil.decode(in));
    }
}
