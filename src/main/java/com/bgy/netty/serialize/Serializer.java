package com.bgy.netty.serialize;

/**
 * @author bgy
 * @date 2020/1/11 23:54
 */
public interface Serializer {
    /**
     * 序列化算法
     *
     * @return
     */
    byte serializerAlgorithm();

    /**
     * 序列化
     * java对象转二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     * 二进制转java对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
