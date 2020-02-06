package com.bgy.netty.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bgy
 * @date 2020/1/16 23:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<K, V> {
    private K v1;
    private V v2;

}
