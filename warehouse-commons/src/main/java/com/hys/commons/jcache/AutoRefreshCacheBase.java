/**
 * 
 */
package com.hys.commons.jcache;

import com.hys.commons.jcache.monitor.Visitable;

/**
 * 
 */
public abstract class AutoRefreshCacheBase<K, V> implements Cache<K, V>, Visitable
{

    abstract public long getReflushTime();

    public abstract String getReflushingStatus();

}
