
/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.                                    
 * Project: Xkins                                                         
 * (#)XkinsLogger.java                  
 *                                         
 * Permission is granted to copy, distribute and/or modify this document  
 * under the terms of the GNU Free Documentation License, Version 1.1 or  
 * any later version published by the Free Software Foundation; with no   
 * Invariant Sections, with no Front-Cover Texts.                     
 * A copy of the license is included in the section entitled 
 * "GNU Free Documentation License".                                 
 * ====================================================================
 */            
package net.jcreate.xkins;

import org.apache.log4j.Logger;

/**
 * Class para manejar el logging
 * @author Guillermo Meyer
 */
public class XkinsLogger {
    //~ Static fields/initializers -----------------------------------------------------------------
	public final static String XKINS_CATEG = "XKINS";
    private static Logger logger = null;

    //~ Methods ------------------------------------------------------------------------------------

	/**
	 * returns the logger
	 */
	public static Logger getLogger() {
		return getLogger(XKINS_CATEG);
	}

    /**
     * returns the logger
     */
    public static Logger getLogger(String categ) {
        if (logger == null) {
            logger = Logger.getLogger(categ);
        }
        return logger;
    }
    
    /**
     * This is a helper method to trace for debugging purpouses
     * @param message
     */
    public static void trace(String message) {
        System.out.println(Thread.currentThread().toString() + " " + message);
    }
}
