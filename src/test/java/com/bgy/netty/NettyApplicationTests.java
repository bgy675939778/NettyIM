package com.bgy.netty;

import com.bgy.netty.codec.PacketCodec;
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

        PacketCodec packetCodec = new PacketCodec();
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        packetCodec.encode(byteBuf,loginRequestPacket);

        AbstractPacket abstractPacket = packetCodec.decode(byteBuf);

        System.out.println(abstractPacket);
    }
}
