package com.hys.commons.redis;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

import com.hys.commons.conf.FileUpdate;
import com.hys.commons.conf.NotifyFileUpdate;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.util.LogicUtil;

/**
 * redis操作客户端工具类
 * 
 */
public class JedisPoolFactory
{
    private static Logger log = LogProxy.getLogger(JedisPoolFactory.class);

    private static String configFile = "jedisconf";
    /**
     * 主服务器数据源连接池,主要用于写操作
     */
    private static JedisPool jedisPool = null;

    /**
     * 数据源共享连接池,主要用于读取数据
     */
    private static ShardedJedisPool shardedJedisPool = null;

    static
    {
        loadXmlConfig();

        NotifyFileUpdate.registInterface(new FileUpdate()
        {
            @Override
            public void updateFile(String fileName)
            {
                if (fileName.startsWith(configFile))
                {
                    log.debug("updateFile = " + fileName);
                    loadXmlConfig();
                }
            }
        });
    }

    /**
     * @author: smartlv
     * @date: 2014年2月17日下午3:36:30
     */
    private static void loadXmlConfig()
    {
        DefaultListableBeanFactory context = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:autoconf/jedisconf.xml");

        initJedisPool(context);
        initShardedJedisPool(context);
    }

    private static void initJedisPool(DefaultListableBeanFactory context)
    {
        JedisConf conf = (JedisConf) context.getBean("jedisConf");

        JedisShardInfo jsInfo = null;
        if (LogicUtil.isNullOrEmpty(conf.getJsInfo()))
        {
            return;
        }
        jsInfo = conf.getJsInfo().get(0);

        jedisPool = new JedisPool(conf.getPoolConfig(), jsInfo.getHost(), jsInfo.getPort(), jsInfo.getSoTimeout(),
                jsInfo.getPassword());
    }

    private static void initShardedJedisPool(DefaultListableBeanFactory context)
    {
        JedisConf conf = (JedisConf) context.getBean("shardedJedisConf");

        shardedJedisPool = new ShardedJedisPool(conf.getPoolConfig(), conf.getJsInfo(), conf.getAlgo(),
                Pattern.compile(conf.getPattern()));
    }

    public static JedisPool getJedisPool()
    {
        return jedisPool;
    }

    public static ShardedJedisPool getShardedJedisPool()
    {
        return shardedJedisPool;
    }

    /**
     * 打开一个普通jedis数据库连接
     * 
     * @param jedis
     */
    public static Jedis openJedis() throws Exception
    {
        if (LogicUtil.isNull(jedisPool))
        {
            return null;
        }

        return jedisPool.getResource();
    }

    /**
     * 打开一个分布式jedis从数据库连接
     * 
     * @throws
     * @author: yong
     * @date: 2013-8-30下午08:41:23
     */
    public static ShardedJedis openShareJedis() throws Exception
    {
        if (LogicUtil.isNull(shardedJedisPool))
        {
            return null;
        }

        return shardedJedisPool.getResource();
    }

    /**
     * 归还普通或分布式jedis数据库连接(返回连接池)
     * 
     * @param p
     *        连接池
     * @param jedis
     *        客户端
     */

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void returnJedisCommands(Pool p, JedisCommands jedis)
    {
        if (LogicUtil.isNull(p) || LogicUtil.isNull(jedis))
        {
            return;
        }

        try
        {
            p.returnResource(jedis);// 返回连接池
        }
        catch (Exception e)
        {
            log.error("return Jedis or SharedJedis to pool error", e);
        }
    }

    /**
     * 释放redis对象
     * 
     * @param p
     *        连接池
     * @param jedis
     *        redis连接客户端
     * @throws
     * @author: yong
     * @date: 2013-8-31下午02:12:21
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void returnBrokenJedisCommands(Pool p, JedisCommands jedis)
    {
        if (LogicUtil.isNull(p) || LogicUtil.isNull(jedis))
        {
            return;
        }

        try
        {
            p.returnBrokenResource(jedis); // 获取连接，使用命令时，当出现异常时要销毁对象
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }

}
