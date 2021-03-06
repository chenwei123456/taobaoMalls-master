package com.taobao.sso.dao;

/**
 * Created by chenwei on 2017/10/27.
 */
public interface JedisClient {

    String get(String key);
    String set(String key,String value);
    String hget(String hkey,String key,String value);
    long hset(String hkey,String key,String value);
    long incr(String key);
    long expire(String key,int second);
    long ttl(String key);
    long  del(String key);
    long  hdel(String hkey,String key);
}
