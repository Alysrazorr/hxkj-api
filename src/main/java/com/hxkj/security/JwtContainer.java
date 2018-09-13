package com.hxkj.security;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengsd
 * @version 2018/9/13 19:50
 */
@Component
public class JwtContainer {

    public static Map<String, String> loggedInUsers = new HashMap<>();
}
