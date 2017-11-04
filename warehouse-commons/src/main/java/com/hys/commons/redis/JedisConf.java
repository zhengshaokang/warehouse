package com.hys.commons.redis;

import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.util.Hashing;

/**
 * redis连接池的相关配置
 * 
 */
public class JedisConf
{
    // 普通连接池或分布式连接的连接配置
    private List<JedisShardInfo> jsInfo;

    private JedisPoolConfig poolConfig;// 池化的相关配置

    private Hashing algo;// 分布式服务器轮询算法

    private String pattern;// 分布式key的匹配模式

    public JedisPoolConfig getPoolConfig()
    {
        return poolConfig;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig)
    {
        this.poolConfig = poolConfig;
    }

    public List<JedisShardInfo> getJsInfo()
    {
        return jsInfo;
    }

    public void setJsInfo(List<JedisShardInfo> jsInfo)
    {
        this.jsInfo = jsInfo;
    }

    public Hashing getAlgo()
    {
        return algo;
    }

    public void setAlgo(Hashing algo)
    {
        this.algo = algo;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

}
