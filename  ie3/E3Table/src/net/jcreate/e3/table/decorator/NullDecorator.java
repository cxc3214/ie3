package net.jcreate.e3.table.decorator;

import net.jcreate.e3.table.Cell;

public class NullDecorator extends AbstractDecorator{

	public Object decorate(Object pValue, Cell pCell) throws Exception {
        if ( pValue == null ){
        	return "";
        } else {
        	return pValue;
        }
	}

}
