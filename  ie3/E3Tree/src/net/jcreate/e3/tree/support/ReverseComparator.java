package net.jcreate.e3.tree.support;

import java.util.Comparator;

/**
 * 反转排序器
 * @author 黄云辉
 *
 */
public class ReverseComparator implements Comparator{
	
	private Comparator comparator;
	public ReverseComparator(Comparator pComparator){
		if ( pComparator == null ){
			throw new RuntimeException("Comparator is null");
		}
		this.comparator = pComparator;
	}
	public int compare(Object o1, Object o2) {
		int result = comparator.compare(o1, o2);
		if ( result > 0 ){
			return -1;
		} else if ( result < 0 ){
			return 1;
		} else {
			return 0;
		}
	}

}
