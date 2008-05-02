package net.jcreate.e3.commons.id;

/**
 * @author 黄云辉
 *
 */
public interface IDGenerator {
  public String create() throws CreateIDException;
}
