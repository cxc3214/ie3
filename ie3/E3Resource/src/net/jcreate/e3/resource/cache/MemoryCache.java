package net.jcreate.e3.resource.cache;

import java.util.HashMap;
import java.util.Map;

import net.jcreate.e3.resource.CacheException;

public class MemoryCache extends  AbstractCache {

	private Map map = new HashMap();
	public void put(Object key, Object pValue) throws CacheException{
		map.put(key, pValue);	
	}

	public Object get(Object key)  throws CacheException{
	   return map.get(key);
	}

	public void remove(Object key)  throws CacheException{
		map.remove(key);
	}

	public void clear() throws CacheException{
       map.clear();   		
	}

}
