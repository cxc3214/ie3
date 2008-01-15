package net.jcreate.e3.tree.loader;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import net.jcreate.e3.tree.util.ZipUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 负责装载XTree需要的资源.
 * @author Grace
 *
 */
public class ResourcesLoader {
	private final static Log logger = LogFactory.getLog ( ResourcesLoader.class );
	
	/**
	 * 
	 * @param pWebHome
	 * @throws LoadResourcesException
	 */
  public static void load(final String pWebHome) throws LoadResourcesException{	
	  final String XTREE_RESOURCES = "net/jcreate/e3/tree/xtree/xtree.zip";
	  final String YUI_RESOURCES = "net/jcreate/e3/tree/yui/yui.zip";
	  final String EXT_RESOURCES= "net/jcreate/e3/tree/ext/ext.zip";
	  logger.info("开始导出E3.Tree资源文件...");
	  loadResource(XTREE_RESOURCES,pWebHome + "/e3/tree");
	  logger.info("导出xtree资源文件成功!");	  
	  loadResource(YUI_RESOURCES,pWebHome + "/e3/tree");
	  logger.info("导出yui资源文件成功!");
	  loadResource(EXT_RESOURCES,pWebHome + "/e3/tree");
	  logger.info("导出ext资源文件成功!");
	  logger.info("导出E3.Tree资源文件成功!");
  }
  
  private static void loadResource(String pResources, String pDestPath) throws LoadResourcesException{
	   try {
 	   InputStream xtreeResouce = ResourcesLoader.class.getClassLoader().
		 getResourceAsStream(pResources);
	   ZipInputStream zis = new ZipInputStream(
					   xtreeResouce);
		ZipUtils.unzip(zis, pDestPath);
	} catch (Exception e) {
		final String MSG =
			"解压缩xtree资源文件:" + pResources + " 到 " + pDestPath + "失败!";
		logger.error(MSG, e);
		throw new LoadResourcesException(MSG, e);
	}
	  
  }
}
