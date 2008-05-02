package net.jcreate.e3.commons.id;

public interface SequenceGenerator {
  public long next() throws CreateSequnceException;
}
