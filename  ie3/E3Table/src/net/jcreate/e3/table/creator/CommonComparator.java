package net.jcreate.e3.table.creator;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.comparators.ComparableComparator;

/**
 * 由于BeanComparator的实现有问题:没有处理值为null的情况
 * 所以参考BeanComparator的代码写了这个通用排序类.
 * @author 黄云辉
 *
 */
public class CommonComparator implements Comparator {

	    private String attribute; 
	    private Comparator comp = new ComparableComparator(); 

	    public CommonComparator(String attrib) { 
	        this.attribute = attrib; 
	    } 

	    public int compare(Object o1, Object o2) { 
	        if(o1 == null) { 
	            return 1; 
	        }  
	        if(o2 == null) { 
	            return -1; 
	        } 
	         
	        try { 
	            Object ret1 = PropertyUtils.getProperty(o1, this.attribute); 
	            Object ret2 = PropertyUtils.getProperty(o2, this.attribute);
	            if ( ret1 == null ){
	            	return 1;
	            }
	            if ( ret2 == null ){
	            	return -1;
	            }
	            return this.comp.compare(ret1, ret2); 
	        } catch(Exception e) { 
	            return 0; 
	        } 
	    } 
	}  

