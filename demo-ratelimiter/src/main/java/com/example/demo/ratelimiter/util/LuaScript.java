package com.example.demo.ratelimiter.util;

public class LuaScript {

    public static final String LuaSecondsScript = " local current; "
            + " current = redis.call('incr',KEYS[1]); "
            + " if tonumber(current) == 1 then "
            + " redis.call('expire',KEYS[1],ARGV[1]) "
            + " end ";
    public static final String LuaPeriodScript = " local current;"
            + " redis.call('zadd',KEYS[1],ARGV[1],ARGV[2]);"
            + "current = redis.call('zcount', KEYS[1], '-inf', '+inf');"
            + " if tonumber(current) == 1 then "
            + " redis.call('expire',KEYS[1],ARGV[3]) "
            + " end ";
}
