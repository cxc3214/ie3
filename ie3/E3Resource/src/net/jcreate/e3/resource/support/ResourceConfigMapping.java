package net.jcreate.e3.resource.support;

import net.jcreate.e3.resource.ResourceException;

public interface ResourceConfigMapping {
  public ResourceConfig mapping(String pUri) throws ResourceException;
}
