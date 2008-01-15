package net.jcreate.e3.samples.tree;

import net.jcreate.e3.tree.UncodeException;
import net.jcreate.e3.tree.UserDataUncoder;

public class OrgUncoder implements UserDataUncoder {
	public Object getID(Object pUserData) throws UncodeException {
		Org org = (Org)pUserData;
		return org.getId();
	} 
	public Object getParentID(Object pUserData) throws UncodeException {
		Org org = (Org)pUserData;
		return org.getParentId();
	}
	
}
