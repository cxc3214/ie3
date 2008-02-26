package net.jcreate.e3.tree.taglib;

import net.jcreate.e3.tree.ext.ExtLoadTreeBuilder;
import net.jcreate.e3.tree.ext.ExtTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeNode;
import net.jcreate.e3.tree.util.ClassUtils;
import net.jcreate.e3.tree.xtree.CheckXLoadTreeBuilder;
import net.jcreate.e3.tree.xtree.CheckXTreeBuilder;
import net.jcreate.e3.tree.xtree.CompositeXLoadTreeBuilder;
import net.jcreate.e3.tree.xtree.CompositeXTreeBuilder;
import net.jcreate.e3.tree.xtree.RadioXLoadTreeBuilder;
import net.jcreate.e3.tree.xtree.RadioXTreeBuilder;
import net.jcreate.e3.tree.xtree.XLoadTreeBuilder;
import net.jcreate.e3.tree.xtree.XTreeBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BuilderFactory {
	
	private static final Log logger = LogFactory.getLog( NodeFactory.class );

private BuilderFactory(){
	
}

public static WebTreeBuilder getInstance(String pClassName){

	if ( "default".equalsIgnoreCase(pClassName) ){
		return new CompositeXTreeBuilder();
	}
	
	if ( "XTree".equalsIgnoreCase(pClassName) ){
		return new XTreeBuilder();
	}
	if ( "XLoadTree".equalsIgnoreCase(pClassName) ){
		return new XLoadTreeBuilder();
	}
	
	if ( "RadioXTree".equalsIgnoreCase(pClassName) ){
		return new RadioXTreeBuilder();
	}
	if ( "RadioXLoadTree".equalsIgnoreCase(pClassName) ){
		return new RadioXLoadTreeBuilder();
	}
	
	if ( "CheckXTree".equalsIgnoreCase(pClassName) ){
		return new CheckXTreeBuilder();
	}
	if ( "CheckXLoadTree".equalsIgnoreCase(pClassName) ){
		return new CheckXLoadTreeBuilder();
	}
	
	if ( "CompositeXTree".equalsIgnoreCase(pClassName) ){
		return new CompositeXTreeBuilder();
	}	
	if ( "CompositeXLoadTree".equalsIgnoreCase(pClassName) ){
		return new CompositeXLoadTreeBuilder();
	}
	
	if ( "ExtTree".equalsIgnoreCase(pClassName) ){
		return new ExtTreeBuilder();
	}
	if ( "ExtLoadTree".equalsIgnoreCase(pClassName) ){
		return new ExtLoadTreeBuilder();
	}
	
	try {
		Object obj = ClassUtils.getNewInstance(pClassName);
		if ( obj instanceof WebTreeNode == false ){
			final String msg = 
				"类:" + pClassName + "的父类不是:" + WebTreeBuilder.class.getName();
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
		return (WebTreeBuilder)obj;
	} catch (Exception e) {
       final String msg =
    	   "创建类:" + pClassName + "实例失败";
       
       throw new CreateObjectException(msg, e);
     
	}
}
}
