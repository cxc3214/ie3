package net.jcreate.e3.samples.tree;

import java.util.List;

import net.jcreate.e3.core.BusinessException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeService treeService =  TreeBeanFactory.getTreeService();
		try {
			DBUtils.initDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List orgs = null;
		try {
			orgs = treeService.getOrgs();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<orgs.size(); i++){
			Org org = (Org)orgs.get(i);
			System.out.println(org.getName());
		}
		
		
	}

}
