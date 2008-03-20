package net.jcreate.e3.resource.support;

import net.jcreate.e3.resource.ResourceException;
import net.jcreate.e3.resource.ResourceLoader;

public interface LoaderMapping {
  public ResourceLoader mapping(String pName) throws ResourceException;
}
