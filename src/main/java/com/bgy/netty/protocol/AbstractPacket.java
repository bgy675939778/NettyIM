package com.bgy.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author bgy
 * @date 2020/1/13 0:28
 */
@Data
@ToString
public abstract class AbstractPacket extends Packet{
    /**
     * 获取操作指令
     *
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
