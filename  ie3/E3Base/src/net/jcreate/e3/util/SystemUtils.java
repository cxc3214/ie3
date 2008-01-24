package net.jcreate.e3.util;

public class SystemUtils {

	private SystemUtils(){
		
	}
	 
	/**
	 * 获取项目文件路径
	 * @return 项目文件路径
	 */
	public static String getPrjPath(){
		return System.getProperty("user.dir");
	}

}
