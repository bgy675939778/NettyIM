package com.bgy.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.bgy.netty.serialize.Serializer;

/**
 * @author bgy
 * @date 2020/1/11 23:58
 */
public class JsonSerializer implements Serializer {
    private static final byte JSON_SERIALIZER = 1;

    @Override
    public byte serializerAlgorithm() {
        return JSON_SERIALIZER;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
