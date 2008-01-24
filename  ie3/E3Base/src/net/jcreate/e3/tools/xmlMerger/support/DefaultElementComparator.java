package net.jcreate.e3.tools.xmlMerger.support;

import java.util.List;

import net.jcreate.e3.tools.xmlMerger.ElementComparator;

import org.dom4j.Element;
import org.dom4j.Attribute;

/**
 * 只要属性名和属性的全部属性相等，就认为相等
 * @author 黄云辉
 *
 */
public class DefaultElementComparator implements ElementComparator {

	public boolean compare(Element pTargetElement, Element pSrcElement) {
		if ( pTargetElement == pSrcElement ){
			return true;
		}
		if ( pTargetElement == null ){
			return false;
		}
		if ( pSrcElement == null ){
			return false;
		}
		if ( pTargetElement.getPath().equals( pSrcElement.getPath() ) == false){
			return false;
		}
		List targetAttributes = pTargetElement.attributes();
		List srcAttributes = pSrcElement.attributes();
		if ( targetAttributes.size() != srcAttributes.size() ){
			return false;
		}
		if ( srcAttributes.size() == 0 && targetAttributes.size() == 0 ){
			return true;
		}
		int sameAttributeNum = 0;
		for(int i=0; i<srcAttributes.size(); i++){
			Attribute srcAttr = (Attribute)srcAttributes.get(i);
			String srcName = srcAttr.getName();
			String srcValue = srcAttr.getValue();
			for(int j=0; j<targetAttributes.size(); j++){
				Attribute targetAttr = (Attribute)targetAttributes.get(i);
				String targetName = targetAttr.getName();
				String targetValue = targetAttr.getValue();
				if ( srcName.equalsIgnoreCase(targetName) == false ){
					continue;
				}
				if ( srcValue.trim().equals(targetValue.trim()) == false ){
					return false;
				} else {
					sameAttributeNum++;
					break;
				}
			}
		}
		if ( sameAttributeNum == srcAttributes.size() ){
			return true;
		} else {
		  return false;
		}
	}

}
