package net.jcreate.e3.tools.xmlMerger.support;

import net.jcreate.e3.tools.xmlMerger.AttributeMerger;

import org.dom4j.Attribute;

public class IgnoreAttributeMerger implements AttributeMerger{

	public void merge(Attribute pTargetAttribute, Attribute pSrcAttribute) {
        return;
	}
}
