package net.jcreate.e3.resource.support;

import net.jcreate.e3.resource.ResourceException;
import net.jcreate.e3.resource.ResourceHandler;

public interface HandlerMapping {
	
  public ResourceHandler mapping(String pName) throws ResourceException;
  
}
