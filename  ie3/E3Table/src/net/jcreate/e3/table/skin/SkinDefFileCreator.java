package net.jcreate.e3.table.skin;

/**
 * @author 刘帅
 * 负责创建皮肤定义文件
 */
public class SkinDefFileCreator {

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
	 *              如:pWebHome 为 c:/tomcat5/webapps/e3
	 *                 pDirs   的值为new String[]{"/skina", "/skinb"}
	 *              那么扫描的实际路径是    c:/tomcat5/webapps/e3/skina 和c:/tomcat5/webapps/e3/skinb
	 *              
	 * @param pSaveFile 皮肤定义文件的输出文件名,包括路径.如: c:/e3/Skin.def.xml, 
	 *                 说明:如果存在指定皮肤文件pSaveFile,直接覆盖就可以.
	 * @throws SkinException 创建皮肤文件时出现异常
	 */
  public void create(String pWebHome, String[] pDirs, String pSaveFile)  throws SkinException{
	  
  }
}
