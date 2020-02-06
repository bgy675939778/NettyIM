package com.bgy.netty.codec;

import com.bgy.netty.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author bgy
 * @date 2020/1/13 0:13
 * @desciption 拆包器
 * <p>
 * * 数据包格式：
 * * |魔数 |版本号|序列化算法|指令|数据长度|数据 |
 * * |4字节|1字节 |  1字节  |1字节| 4字节 |n字节|
 */
public class Unpacker extends LengthFieldBasedFrameDecoder {
    //4 + 1 + 1 + 1
    private static final int LENGTH_FIELD_OFFSET = 7;
    //数据长度
    private static final int LENGTH_FIELD_LENGTH = 4;

    private static final Packet packet = new Packet();

    public Unpacker() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != packet.getMagicNumber()) {
            System.err.println("数据包不满足当前协议，连接关闭!");
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
