package net.jcreate.e3.tree.taglib;

import net.jcreate.e3.tree.support.WebTreeDynamicNode;
import net.jcreate.e3.tree.support.WebTreeNode;
import net.jcreate.e3.tree.util.ClassUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NodeFactory {
	private static final Log logger = LogFactory.getLog( NodeFactory.class );
	
	private NodeFactory(){
		
	}
	
	public static WebTreeNode getInstance(String pClassName){
		if ( "default".equalsIgnoreCase(pClassName) ){
			return new WebTreeNode();
		}
		if ( "dynamic".equalsIgnoreCase(pClassName) ){
			return new WebTreeDynamicNode();
		}
		
		try {
			Object obj = ClassUtils.getNewInstance(pClassName);
			if ( obj instanceof WebTreeNode == false ){
				final String msg = 
					"类:" + pClassName + "的父类不是:" + WebTreeNode.class.getName();
				logger.error(msg);
				throw new IllegalArgumentException(msg);
			}
			return (WebTreeNode)obj;
		} catch (Exception e) {
           final String msg =
        	   "创建类:" + pClassName + "实例失败";
           
           throw new CreateObjectException(msg, e);
         
		}
	}
}
