package com.hys.commons.jcache.monitor;

import com.hys.commons.jcache.AutoRefreshCacheBase;
import com.hys.commons.jcache.Cache;
import com.hys.commons.jcache.adv.AdvAutoSaveCache;
import com.hys.commons.jcache.adv.AdvCache;

public interface Visitor {

	void visitAutoRefreshCache(AutoRefreshCacheBase<?, ?> cache, String name);

	void visitAdvAutoSaveCache(AdvAutoSaveCache<?, ?> cache, String name);

	void visitAdvCache(AdvCache<?, ?> cache, String name);

	void visitCustomCache(Cache<?, ?> cache, String name);
}
