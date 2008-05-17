package net.jcreate.e3.table.skin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.jcreate.e3.templateEngine.support.TemplateType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 刘帅
 * 负责创建皮肤定义文件
 */
public class SkinDefFileCreator {

	private static final Log logger = LogFactory.getLog( SkinDefFileCreator.class );
	/**
	 * 到指定路径下扫描皮肤文件Skin.xml,根据返回的结果,产生皮肤定义文件,最后
	 * 将产生的皮肤定义文件保存到指定文件. 注意:文件编码方式采用utf-8.
	 * 皮肤文件格式:
    <?xml version="1.0" encoding="UTF-8"?>
    <xkins>
       <global-processor id="velocity" type="net.jcreate.xkins.processor.VelocityTemplateProcessor"/>
       <skin name="classicLook" url="/store/skins/classicLook" definition="/Skin.xml" />
    </xkins>
    
    
     <skin name="classicLook" url="/store/skins/classicLook" definition="/Skin.xml" />
     这段定义是根据扫描结果生成的,其他部分内容是固定.
     skin元素有3个属性,
       name是皮肤名称,对应扫描到的目录名
       url是皮肤的home路径,值为扫描路径 + "/" + 扫描到的目录名
       definition的值固定是 /Skin.xml
       
     如:扫描结果发现2个皮肤目录classicLook1和classicLook2,那生成的皮肤定义文件可能是下面这个样子:
    <?xml version="1.0" encoding="UTF-8"?>
    <xkins>
       <global-processor id="velocity" type="net.jcreate.xkins.processor.VelocityTemplateProcessor"/>
	   <skin name="classicLook1" url="/store/skins/classicLook1" definition="/Skin.xml" />
	   <skin name="classicLook2" url="/store/skins/classicLook2" definition="/Skin.xml" />
    </xkins>
     
	 * @param pWebHome web应用路径 
	 * @param pDirs 扫描皮肤的路径数组,必须非空,否则抛异常 SkinException
	 *              注意:路径是相对pWebHome的,pWebHome是web应用路径,
	 *              如:pWebHome 为 c:/tomcat5/webapps/e3/
	 *                 pDirs   的值为new String[]{"/skina", "/skinb"}
	 *              那么扫描的实际路径是    c:/tomcat5/webapps/e3/skina 和c:/tomcat5/webapps/e3/skinb
	 *              
	 * @param pSaveFile 皮肤定义文件的输出文件名,包括路径.如: c:/e3/Skin.def.xml, 
	 *                 说明:如果存在指定皮肤文件pSaveFile,直接覆盖就可以.
	 * @throws SkinException 创建皮肤文件时出现异常
	 */
  public static void create(String pWebHome, String[] pDirs, String pSaveFile)  throws SkinException{
	  /**
	   * 当是war应用时,pWebHome会是null
	   */
	if ( pWebHome == null ){
		return;
	}
	if ( pDirs == null ){
	  throw new SkinException("譬如搜索路径不能为空null"); 	
	}
	if ( pDirs.length == 0 ){
		throw new SkinException("至少设置一个皮肤搜索路径!");
	}
	if ( pSaveFile == null ){
	  throw new SkinException("皮肤保存路径不能为空null");	  
	}
	if ( logger.isDebugEnabled() ){
      logger.debug("WEB HOME :" + pWebHome);
      for(int i=0; i<pDirs.length; i++){
    	  logger.debug("皮肤搜索路径:" + pDirs[i]);  
      }
      logger.debug("皮肤保存文件路径:" + pSaveFile);
	}
	List skinDefs  = new ArrayList();
	for(int i=0; i<pDirs.length; i++){
		final String dir = pDirs[i];
		String[] skins = SkinScaner.scan(pWebHome + dir);
		for(int j=0; j<skins.length; j++){
		    String skinName = skins[j];
			SkinDef skinDef = new SkinDef();
			skinDef.setSkinHome(dir + "/" + skinName);
			skinDef.setName(skinName);
			skinDefs.add(skinDef);
		}
	}
	logger.debug("开始生成皮肤定义文件内容");
	net.jcreate.e3.templateEngine.Template template = new net.jcreate.e3.templateEngine.support.DefaultTemplate();
	template.setResource(TEMPLATE_FILE);
	net.jcreate.e3.templateEngine.Context context = new net.jcreate.e3.templateEngine.support.DefaultContext();
	context.put("skinDefs", skinDefs);
	net.jcreate.e3.templateEngine.TemplateEngine templateEngine = net.jcreate.e3.templateEngine.support.TemplateEngineFactory.getInstance(TemplateType.VELOCITY);
	java.io.StringWriter writer = new java.io.StringWriter();
	templateEngine.mergeTemplate(template, context, writer);
	logger.debug("生成皮肤定义文件内容成功!");

	logger.debug("开始保存皮肤定义文件!");
	FileOutputStream fos = null;
    try{
      fos = new FileOutputStream(pSaveFile);		
      fos.write(writer.toString().getBytes( FILE_ENCODING) );
      fos.flush();
    }catch(Exception ex){
	     final String MSG =
		"生成皮肤定义文件:" + pSaveFile + "失败!";
	     logger.error(MSG, ex);
	     throw new SkinException(MSG, ex); 
    }finally{
    	if ( fos != null ){
    		try {
				fos.close();
			} catch (IOException e) {
				final String MSG = "关闭文件:" + pSaveFile + "失败!";
				logger.error(MSG);
			}
    	}
    }
    logger.debug("保存皮肤定义文件成功!");
  }
  private static final String FILE_ENCODING = "utf-8";
  private static final String TEMPLATE_FILE = "net/jcreate/e3/table/skin/SkinDefTemplate.vm";
  
	public static void main(String[] args)
	{
		SkinDefFileCreator.create(
				"E:/eclipse312/eclipse/workspace/E3Samples/WebRoot",
				new String[]{"/e3/table/skins"}, 
				"E:/eclipse312/eclipse/workspace/E3Samples/WebRoot/test.xml");
	}
  
}
