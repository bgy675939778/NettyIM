package com.bgy.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import com.bgy.netty.serialize.impl.JsonSerializer;
import lombok.Data;

/**
 * @author bgy
 * @date 2020/1/11 23:40
 * @desciption: 自定义协议数据包
 * <p>
 * 格式：
 * |魔数 |版本号|序列化算法|指令|数据长度|数据 |
 * |4字节|1字节 |  1字节  |1字节| 4字节 |n字节|
 */
@Data
public class Packet {
    /**
     * 魔数
     */
    @JSONField(deserialize = false, serialize = false)
    private int magicNumber = 0x12345678;

    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    /**
     * 序列化算法
     * 当前直接默认为JSON序列化，如需多种，可像command一样扩展
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte serializerAlgorithm = new JsonSerializer().serializerAlgorithm();

}
