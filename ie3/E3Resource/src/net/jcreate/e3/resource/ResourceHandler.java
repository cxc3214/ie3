package net.jcreate.e3.resource;

/**
 * 资源Handler,负责资源处理.如资源压缩,资源混淆等处理
 * 注意： 如果是gzip压缩成功，需要设置 resource.setGzip(true) 
 * @author 黄云辉
 *
 */
public interface ResourceHandler {
  public void handle(Resource pResource) throws HandleResourceException;
}
