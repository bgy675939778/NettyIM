package com.bgy.netty.constants;

/**
 * @author bgy
 * @date 2020/1/12 0:14
 */
public class CommandsConstant {
    public static final Byte LOGIN_REQUEST = 1;

    public static final Byte LOGIN_RESPONSE = 2;

    public static final Byte SINGLE_MESSAGE_REQUEST = 3;

    public static final Byte SINGLE_MESSAGE_RESPONSE = 4;

    public static final Byte LOGOUT_REQUEST = 5;

    public static final Byte LOGOUT_RESPONSE = 6;

    public static final Byte CREATE_GROUP_REQUEST = 7;

    public static final Byte CREATE_GROUP_RESPONSE = 8;

    public static final Byte LIST_GROUP_MEMBERS_REQUEST = 9;

    public static final Byte LIST_GROUP_MEMBERS_RESPONSE = 10;

    public static final Byte JOIN_GROUP_REQUEST = 11;

    public static final Byte JOIN_GROUP_RESPONSE = 12;

    public static final Byte QUIT_GROUP_REQUEST = 13;

    public static final Byte QUIT_GROUP_RESPONSE = 14;

    public static final Byte GROUP_MESSAGE_REQUEST = 15;

    public static final Byte GROUP_MESSAGE_RESPONSE = 16;
}
