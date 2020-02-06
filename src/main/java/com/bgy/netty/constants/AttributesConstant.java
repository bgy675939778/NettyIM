package com.bgy.netty.constants;

import com.bgy.netty.session.UserSession;
import io.netty.util.AttributeKey;

/**
 * @author bgy
 * @date 2020/1/14 22:57
 */
public class AttributesConstant {
    public static final AttributeKey<UserSession> SESSION = AttributeKey.newInstance("session");
}
