package net.jcreate.e3.table.loader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import net.jcreate.e3.table.util.ZipUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 负责装载Table需要的资源.
 * @author Grace
 *
 */
public class ResourcesLoader {
	private final static Log logger = LogFactory.getLog ( ResourcesLoader.class );
	
	/**
	 * 版本控制文件
	 */
	private static final String VERSION_FILE = "E3Table.version.properties";

	private static double getNewVersion(final String pWebHome)throws LoadResourcesException{
		
		java.util.Properties props = new java.util.Properties();
		java.io.InputStream is = null;
		try {
			is = ResourcesLoader.class.getResourceAsStream(VERSION_FILE);			
			props.load(is);
		} catch (IOException e) {
			final String MSG =
				"读版本控制文件:" + VERSION_FILE + "失败!";
			logger.error(MSG, e);
			throw new LoadResourcesException(MSG, e);
		}finally{
			if ( is != null ){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.debug("关闭文件:" + VERSION_FILE + "输入流失败!");
				}
			}
		}
		
		String version = props.getProperty(VERSION_KEY);
		logger.debug("E3Table新的资源版本号是:" + version);
		double dblVersion = Double.parseDouble(version);
		return dblVersion;
		
	}
	
	private static String getVersionFile(final String pWebHome){
		return pWebHome + "/e3/table/E3Table.version.properties";
	}
	private static final String VERSION_KEY = "e3.table.version";
	private static boolean isNeedUpdate(final String pWebHome)throws LoadResourcesException{
		String versionFile = getVersionFile( pWebHome );
		java.io.File oldVersionFile = new java.io.File(versionFile);
		if ( oldVersionFile.exists() == false ){
			return true;
		}
		double newVersion = getNewVersion(pWebHome);
		java.util.Properties fileProperties = new java.util.Properties();
		java.io.InputStream fileIS = null;
		try {
			fileIS = new java.io.FileInputStream(versionFile);
			fileProperties.load(fileIS);
		} catch (FileNotFoundException e) {
			final String MSG =
				"没有发现版本控制文件:" + versionFile ;
			logger.error(MSG, e);
			throw new LoadResourcesException(MSG, e);
		} catch(IOException ex){
			final String MSG =
				"读版本控制文件:" + versionFile + "失败!";
			logger.error(MSG, ex);
			throw new LoadResourcesException(MSG, ex);
		}
		String strVersion = fileProperties.getProperty(VERSION_KEY);
		if ( strVersion == null ){
			return true;
		}
		double oldVersion = 0;
		try{
		 oldVersion = Double.parseDouble(strVersion);//因为是外部版本控制文件，可能被破坏，所以在这做这种异常处理
		}catch(Exception ex){
			return true;
		}
		
		logger.debug("E3Table旧的资源版本号是:" + strVersion);
		if ( newVersion > oldVersion ){
			return true;
		} else {
			return false;
		}
	}
	
	private static void exportVersionFile(final String pWebHome) throws LoadResourcesException{
		String versionFile = getVersionFile( pWebHome );
		java.io.InputStream 
			in = ResourcesLoader.class.getResourceAsStream(VERSION_FILE);
		try{
		FileOutputStream fos=new FileOutputStream(versionFile);		
		final int cache = 1024;
   	     byte[] b = new byte[cache];
	   	 int aa = 0;
			while ((aa = in.read(b)) != -1) {
			    fos.write(b, 0, aa);
		   }
		}catch(Exception ex){
			final String MSG =
				"导出版本控制文件:" + versionFile + "失败!";
			logger.warn(MSG);//下次还会重新释放资源，但是不影响系统正常运行.
		}
		
	}
	/**
	 * 
	 * @param pWebHome
	 * @throws LoadResourcesException
	 */
  public static void load(final String pWebHome) throws LoadResourcesException{
	  //当系统是war包时,pWebHome会是空
	  if ( pWebHome == null ){
		  return;
	  }
	  if ( isNeedUpdate(pWebHome) == false){
		  logger.debug("版本未发生变化，不需要导出e3.table资源包!");
		  return;
	  }
	  final String RESOURCES = "net/jcreate/e3/table/loader/Table.zip";
	  logger.info("开始导出E3.Table资源文件...");
	  loadResource(RESOURCES,pWebHome + "/e3/table");  
	  logger.info("导出E3.Table资源文件成功!");
	  //必须在最后导出版本控制文件，这样可以避免导出过程出错，版本不能得到更新的情况.
	  exportVersionFile(pWebHome);
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
			"解压缩资源文件:" + pResources + " 到 " + pDestPath + "失败!";
		logger.error(MSG, e);
		throw new LoadResourcesException(MSG, e);
	}
	  
  }
}
