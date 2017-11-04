package com.hys.commons.jcache;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public final class CacheWrapper
{
    private static final Logger logger = LoggerFactory.getLogger("jcache");

    private Cache cache;

    private long lastCheckTime = System.currentTimeMillis();

    private long checkInterval = 1000 * 60 * 60;

    public CacheWrapper(Cache cache, long checkInterval)
    {
        this.cache = cache;
        this.checkInterval = checkInterval;
        if (this.checkInterval <= 0)
        {
            this.checkInterval = Long.MAX_VALUE;
        }
    }

    public CacheWrapper(Cache cache, long checkInterval, int peakShiftingRange)
    {
        this(cache, checkInterval);
        Random r = new Random();
        int shift = r.nextInt(peakShiftingRange);
        logger.debug("jcache peakShifting: " + shift);
        lastCheckTime += shift * 1000;
    }

    public Cache getCache()
    {
        return cache;
    }

    void setCache(Cache c)
    {
        cache = c;
    }

    public boolean cleanUp()
    {
        if (System.currentTimeMillis() - this.lastCheckTime < this.checkInterval)
        {
            return false;
        }
        int n = 0;
        try
        {
            n = cache.cleanUp();
        }
        finally
        {
            lastCheckTime = System.currentTimeMillis();
            logger.debug("cleanUp, remove " + n + " nodes.");
        }
        return true;
    }

    /**
     * @param lastCheckTime
     *        the lastCheckTime to set
     */
    public void setLastCheckTime(long lastCheckTime)
    {
        this.lastCheckTime = lastCheckTime;
    }
}
