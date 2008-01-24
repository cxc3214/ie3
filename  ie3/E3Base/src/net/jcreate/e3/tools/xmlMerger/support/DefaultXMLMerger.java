package net.jcreate.e3.tools.xmlMerger.support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.jcreate.e3.tools.xmlMerger.AttributeMerger;
import net.jcreate.e3.tools.xmlMerger.ElementComparator;
import net.jcreate.e3.tools.xmlMerger.ElementMergerPolicy;
import net.jcreate.e3.tools.xmlMerger.MergeXMLException;
import net.jcreate.e3.tools.xmlMerger.Policy2AttributeMergerMapper;
import net.jcreate.e3.tools.xmlMerger.Policy2TextMergerMapper;
import net.jcreate.e3.tools.xmlMerger.PolicyDescription;
import net.jcreate.e3.tools.xmlMerger.TextMerger;
import net.jcreate.e3.tools.xmlMerger.XMLMerger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

public class DefaultXMLMerger implements XMLMerger{
	
	private final Log log = LogFactory.getLog( DefaultXMLMerger.class );
	
	private Policy2AttributeMergerMapper  attributeMergerMapper = null;
	private Policy2TextMergerMapper  textMergerMapper = null;
	
	private Map elementMergerPolicys = new HashMap();
	private Map elementComparators = new HashMap();

	public void addElementMergerPolicy(String pPath, ElementMergerPolicy pElementMerger){
		elementMergerPolicys.put(pPath, pElementMerger);
		
	}
	public void addElementComparator(String pPath, ElementComparator pElementComparator){
		elementComparators.put(pPath, pElementComparator);
	}
	
	private final ElementMergerPolicy DEFAULT_ELEMENT_MERGER_POLICY = new DefaultElementMergerPolicy();
	private final ElementComparator DEFAULT_ELEMENT_COMPARATOR = new DefaultElementComparator();
	
	private ElementMergerPolicy getElementMergerPolicy(String pPath){
		/**
		 * TODO:
		 */
	  return DEFAULT_ELEMENT_MERGER_POLICY;	
	}
	
	private ElementComparator getElementComparator(String pPath){
		return DEFAULT_ELEMENT_COMPARATOR;
	}
	
	
	public void merge(Document pTargetDocument, Document pSrcDocument) throws MergeXMLException {
		if ( pTargetDocument == null ){
			throw new MergeXMLException("target document shouldn't be null");
		}
		if ( pSrcDocument == null ){
			throw new MergeXMLException("src document shouldn't be null");
		}
		Element targetElement = pTargetDocument.getRootElement();
		Element srcElement = pSrcDocument.getRootElement();
		
		try {
			mergeElement(targetElement, srcElement);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new MergeXMLException(e.getMessage(), e);
		}
	}
	
	protected void mergeElement(Element pTargetElement, Element pSrcElement) throws Exception{
		mergeAttributes(pTargetElement, pSrcElement);
		if ( pTargetElement.isTextOnly() && pSrcElement.isTextOnly() ){
			ElementMergerPolicy elementMerger = getElementMergerPolicy(pSrcElement.getPath());
			PolicyDescription textPolicy = elementMerger.getTextMergePolicy();
			TextMerger textMerger = this.getTextMergerMapper().map(textPolicy);
			textMerger.merge(pTargetElement, pSrcElement);
			return;
		}
		List srcChildrenElements = pSrcElement.elements();
		List targetChildrenElements = pTargetElement.elements();
		if ( srcChildrenElements == null || srcChildrenElements.isEmpty() ){//不存在子元素
			return;
		}
		if ( targetChildrenElements == null || targetChildrenElements.isEmpty() ){//不存在子元素
			for(int i=0; i<srcChildrenElements.size(); i++){
				Element srcChildElement = (Element)srcChildrenElements.get(i);
				pTargetElement.add((Element)srcChildElement.clone());
			}
			return;
		} 
		
		for(int i=0; i<srcChildrenElements.size(); i++){
			Element srcChildElement = (Element)srcChildrenElements.get(i);
			ElementComparator elementComparator = this.getElementComparator(srcChildElement.getPath());
			String srcChildName = srcChildElement.getName();
			/**
			 * @todo: 如果性能有问题，这里可以进行优化，将srcChildName对应的元素cache起来.
			 */
			Iterator targetElements = pTargetElement.elementIterator(srcChildName);
			while( targetElements.hasNext() ){
				Element targetChildElement = (Element)targetElements.next();
				if ( elementComparator.compare(targetChildElement, srcChildElement) == true ){
					mergeElement(targetChildElement, srcChildElement);
				} else {
					/**
					 * @todo:这里要插入相同元素的上面，避免导致元素顺序出现混   乱的现象.
					 */
					pTargetElement.add((Element)srcChildElement.clone());//添加src元素
				}				
			}
		}
		
	}
	
	protected void mergeAttributes(Element pTargetElement, Element pSrcElement) throws Exception{
		ElementMergerPolicy elementMerger = getElementMergerPolicy(pSrcElement.getPath());
		PolicyDescription attributesMergePolicy = elementMerger.getAttributesMergePolicy();
		if ( PolicyDescription.IGNORE.equals(attributesMergePolicy) ){
			return;
		}
		if ( PolicyDescription.REPLCAE.equals(attributesMergePolicy) ){
			/**
			 * TODO: 执行属性替换
			 */
			return;
		}
		
		if ( PolicyDescription.MERGE.equals(attributesMergePolicy) == false ){
			throw new IllegalArgumentException("unsupported policydescription:" + attributesMergePolicy );
		}
		//执行属性合并.
		List srcAttributes = pSrcElement.attributes();
		if ( srcAttributes == null || srcAttributes.isEmpty()){
			return;			
		}
		
		List targetAttributes = pTargetElement.attributes();
		if ( targetAttributes == null || targetAttributes.isEmpty() ){
			for(int i=0; i<srcAttributes.size(); i++){
				Attribute srcAttr = (Attribute)srcAttributes.get(i);
				pTargetElement.add((Attribute)srcAttr.clone());
			}
		}
		
		for(int i=0; i<srcAttributes.size(); i++){
			Attribute srcAttr = (Attribute)srcAttributes.get(i);
			if ( isContainsAttribute(targetAttributes, srcAttr) == false ){//不包含
				pTargetElement.add((Attribute)srcAttr.clone());
			} else {//包含，则进行属性合并
				Attribute targetAttr = getEqualsAttribute(targetAttributes, srcAttr);
				PolicyDescription attrPolicy = elementMerger.getAttributeMergePolicy(srcAttr.getName());
				AttributeMerger attrMerger = this.getAttributeMergerMapper().map(attrPolicy);
				attrMerger.merge(targetAttr, srcAttr);				
			}
		}
						
		
	}

	/**
	 * 获取相等属性
	 * @param pTargetAttributes
	 * @param pSrcAttribute
	 * @return
	 */
	private Attribute getEqualsAttribute(List pTargetAttributes, Attribute pSrcAttribute){
		if ( pTargetAttributes == null || pTargetAttributes.isEmpty() ){
			return null;
		}
		if ( pSrcAttribute == null ){
			return null;
		}
		for(int i=0; i<pTargetAttributes.size(); i++){
			Attribute targetAttr = (Attribute)pTargetAttributes.get(i);
			if ( isEquals(targetAttr, pSrcAttribute) == true ){
				return targetAttr;
			}
		}
		return null;
		
	}
	/**
	 * 是否包含pSrcAttribute
	 * @param pTargetAttributes
	 * @param pSrcAttribute
	 * @return
	 */
	private boolean isContainsAttribute(List pTargetAttributes, Attribute pSrcAttribute){
		if ( pTargetAttributes == null || pTargetAttributes.isEmpty() ){
			return false;
		}
		if ( pSrcAttribute == null ){
			return false;
		}
		for(int i=0; i<pTargetAttributes.size(); i++){
			Attribute targetAttr = (Attribute)pTargetAttributes.get(i);
			if ( isEquals(targetAttr, pSrcAttribute) == true ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断2个属性是否相等
	 * @param pTargetAttribute
	 * @param pSrcAttribute
	 * @return
	 */
	private boolean isEquals(Attribute pTargetAttribute , Attribute pSrcAttribute ){
		if ( pTargetAttribute == null ){
			return false;
		}
		if ( pSrcAttribute == null ){
			return false;
		}
		String targetAttrName = pTargetAttribute.getName();
		if ( targetAttrName == null ){
			return false;
		}
		
		String srcAttrName = pSrcAttribute.getName();
		return targetAttrName.equalsIgnoreCase(srcAttrName);
	}

	
	public Policy2AttributeMergerMapper getAttributeMergerMapper() {
		if ( this.attributeMergerMapper == null ){
			attributeMergerMapper = new DefaultPolicy2AttributeMergerMapper();
		} 
		return attributeMergerMapper;
	}
	public void setAttributeMergerMapper(
			Policy2AttributeMergerMapper attributeMergerMapper) {
		this.attributeMergerMapper = attributeMergerMapper;
	}
	public Policy2TextMergerMapper getTextMergerMapper() {
		if ( textMergerMapper == null ){
			textMergerMapper = new DefaultPolicy2TextMergerMapper();
		}
		return textMergerMapper;
	}
	public void setTextMergerMapper(Policy2TextMergerMapper textMergerMapper) {
		this.textMergerMapper = textMergerMapper;
	}

}
