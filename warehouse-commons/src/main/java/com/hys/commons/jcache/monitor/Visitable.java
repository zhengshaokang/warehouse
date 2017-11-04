package com.hys.commons.jcache.monitor;

public interface Visitable {

	void accept(Visitor v, String name);

}
