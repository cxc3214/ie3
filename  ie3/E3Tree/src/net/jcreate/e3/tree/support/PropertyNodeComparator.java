package net.jcreate.e3.tree.support;

import net.jcreate.e3.tree.Node;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 按指定属性名进行排序.属性名必须在业务对象中存在
 * @author 黄云辉
 *
 */
public class PropertyNodeComparator 
	extends AbstractNodeComparator {
	
	private final String sortProperty;
	
	public PropertyNodeComparator(String pSortProperty){
		if ( pSortProperty == null ){
			throw new java.lang.RuntimeException("排序属性名不能为空null");
		}
		this.sortProperty = pSortProperty;
	}
	  protected Comparable getComparableProperty(Node pNode) {
			if ( pNode instanceof DefaultNode == false){
				throw new IllegalArgumentException("node type is error, should be DefaultNode!");
			}
			DefaultNode defaultNode = (DefaultNode)pNode;
			Object userData = defaultNode.getUserData();
			if ( userData == null ){
				return new Integer(0);
			}
			Object result = null;
			try {
				result = PropertyUtils.getProperty(userData, this.sortProperty);
			} catch (Exception e) {
				  e.printStackTrace();
				  throw new java.lang.RuntimeException("获取 " + userData.getClass().getName() + "的" + this.sortProperty  + "属性失败!", e);
			}
			if ( result instanceof Comparable == false ){
			  throw new java.lang.RuntimeException(userData.getClass().getName() + "的属性" + this.sortProperty +
					  "没有实现接口:" + Comparable.class.getName() ) ;
			}
			return (Comparable)result;
		}
}
