package com.bgy.netty;

import com.bgy.netty.codec.PacketCodecUtil;
import com.bgy.netty.protocol.AbstractPacket;
import com.bgy.netty.protocol.request.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NettyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void decodeAndEncode(){
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName("a");
        loginRequestPacket.setPassword("b");

        PacketCodecUtil packetCodecUtil = new PacketCodecUtil();
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        packetCodecUtil.encode(byteBuf,loginRequestPacket);

        AbstractPacket abstractPacket = packetCodecUtil.decode(byteBuf);

        System.out.println(abstractPacket);
    }
}
