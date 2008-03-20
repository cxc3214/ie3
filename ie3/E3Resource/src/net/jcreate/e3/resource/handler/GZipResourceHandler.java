package net.jcreate.e3.resource.handler;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.resource.HandleResourceException;
import net.jcreate.e3.resource.HttpHolder;
import net.jcreate.e3.resource.Resource;
import net.jcreate.e3.resource.ResourceHandler;
import net.jcreate.e3.resource.util.WebUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 对资源进行gzip压缩
 * @author 黄云辉
 *
 */
public class GZipResourceHandler implements ResourceHandler{
	
	private final Log logger = LogFactory.getLog( this.getClass() );

	public void handle(Resource pResource) throws HandleResourceException {
		HttpServletRequest request = HttpHolder.getRequest();		
		boolean isSupportedGZIP = WebUtils.isSupportedGzip(request); 
		if ( isSupportedGZIP ) {
			logger.info("正在对资源:" + pResource.getUri() + "进行gzip压缩...");
			int before = pResource.getTreatedData().length;
			try {
				gzip(pResource);
				pResource.setGzip(true);				
			} catch (Exception e) {
				logger.warn("压缩资源:" + pResource.getUri() + "失败！未进行数据压缩!");
				return;
			}
			int after = pResource.getTreatedData().length;
			logger.info("压缩资源:" + pResource.getUri() + "成功." + getGZIPDesc(before, after) );
		}else{
			logger.debug("客户端：" + request.getRemoteAddr() + "不支持gzip压缩，未进行数据压缩!");
            return;             
		}

	}
	
	
	private String getGZIPDesc(int before, int after){
		StringBuffer sb = new StringBuffer();
		sb.append("压缩前: ").append(getSize(before)).append(",压缩后: ").append( getSize(after) ).append(".") ;
		sb.append("压缩比例是：").append(getPrecent(before, after));
		return sb.toString();
	}
	private static String getPrecent(int before, int after){
		DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();		
		df.applyPattern("#.0%");
		BigDecimal bigBeore = BigDecimal.valueOf(before);
		BigDecimal bigAfter = BigDecimal.valueOf(after);
		return df.format( bigBeore.divide(bigAfter, 2, BigDecimal.ROUND_UP).doubleValue() );
	}
	 
	private static String getSize(int pLen){
		DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();
		df.applyPattern("#.0");
		
		BigDecimal bigLen = BigDecimal.valueOf(pLen);
		BigDecimal bigK = BigDecimal.valueOf(1024);
		return df.format(bigLen.divide(bigK,  2, BigDecimal.ROUND_UP).doubleValue()) + " KB";
	}

	private void gzip(Resource pResource)throws Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream outputStream = new GZIPOutputStream( out );
		outputStream.write(pResource.getTreatedData());
		outputStream.finish();
		outputStream.flush();
		outputStream.close();
		pResource.setTreatedData(out.toByteArray());	
		out.close();
	}
	
	public static void main(String[] args){
				
	}

}
