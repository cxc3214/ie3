package net.jcreate.e3.tools.xmlMerger.support;

import net.jcreate.e3.tools.xmlMerger.AttributeMerger;

import org.dom4j.Attribute;

public class CompositeAttributeMerger implements AttributeMerger {

    public static final String DEFAULT_MERGESPLITER = " ";        
    private String mergeSpliter = DEFAULT_MERGESPLITER;
	
	public void merge(Attribute pTargetAttribute, Attribute pSrcAttribute) {
		String targetAttributeValue = pTargetAttribute.getValue();
		String srcAttributeValue = pSrcAttribute.getValue();
		pTargetAttribute.setValue(targetAttributeValue + mergeSpliter + srcAttributeValue);
	}


	public String getMergeSpliter() {
		return mergeSpliter;
	}

	public void setMergeSpliter(String mergeSpliter) {
		this.mergeSpliter = mergeSpliter;
	}

}
