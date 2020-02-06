package com.bgy.netty.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author bgy
 * @date 2020/1/14 22:45
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {
    private String userId;
    private String userName;
}
