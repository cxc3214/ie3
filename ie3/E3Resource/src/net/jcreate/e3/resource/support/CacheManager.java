package net.jcreate.e3.resource.support;

import net.jcreate.e3.resource.Cache;
import net.jcreate.e3.resource.CacheException;
import net.jcreate.e3.resource.Resource;

public class CacheManager {
  private final Cache cache;
  
  public CacheManager(Cache pCache){
	  this.cache = pCache;
  }
  
  public void put(Resource pResource) throws CacheException{
	cache.put(pResource.getUri(), pResource);  
  }
  
  public Resource get(String pUri) throws CacheException{
	  return (Resource)cache.get(pUri);
  }
  
}
