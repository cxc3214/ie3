package net.jcreate.e3.table.skin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author E3
 * 
 */
public class SkinScaner
{

	/**
	 * 默认皮肤定义文件
	 */
	public static final String DEFAULT_SKIN_FILE = "Skin.xml";

	/**
	 * 扫描指定目录下的皮肤目录（存在皮肤文件的目录)
	 * 
	 * @param pDir
	 *            皮肤根目录
	 * @param pSkinFile
	 *            皮肤文件名
	 * @return 如果目录不存在，返回长度为0的字符串数组，否则返回所有皮肤文件的目录 注意：只扫描一层,不进行递归处理. 如： pDir
	 *         为c:\e3 在c:\e3目录下存在 a , b,c 3个目录， 在a,b目录下存在Skin.xml文件，
	 *         那么调用该方法，返回值是 new String[]{"a","b"} 如果a
	 *         目录下还有d文件夹，d文件夹下存在Skin.xml文件，调用该方法的 返回值还是 new String[]{"a","b"},
	 *         因为scan方法不进行递归处理，只 处理pDir目录下的第一层目录.
	 * 
	 * @throws SkinException
	 *             扫描过程出现错误
	 */
	public String[] scan(String pDir, final String pSkinFile)
			throws SkinException
	{
		File root = new File(pDir);
		List listFiles = new ArrayList();
		if (pDir == null || pSkinFile == null)
		{
			return new String[0];
		}

		if (root.exists() == false)
		{
			return new String[0];
		}

		File[] filesOrDirs = root.listFiles();
		for (int i = 0; i < filesOrDirs.length; i++)
		{
			if (filesOrDirs[i].isDirectory() == true)
			{
				File[] childFiles = filesOrDirs[i].listFiles();
				for (int j = 0; j < childFiles.length; j++)
				{
					if (pSkinFile.equals(childFiles[j].getName()))
					{
						listFiles.add(filesOrDirs[i].getName());
					}
				}
			}

		}
		return (String[]) listFiles.toArray(new String[listFiles.size()]);

	}

	/**
	 * 扫描皮肤文件
	 * 
	 * @param pDir
	 *            皮肤根目录
	 * @return
	 * @throws SkinException
	 *             扫描过程出现错误
	 */
	public String[] scan(String pDir) throws SkinException
	{
		return scan(pDir, DEFAULT_SKIN_FILE);
	}

	public static void main(String[] args)
	{
		SkinScaner sc = new SkinScaner();
		String[] arg = sc.scan("C:\\e3");
		for (int i = 0; i < arg.length; i++)
		{
			System.out.println(arg[i]);

		}
	}
}
