/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)Processor.java
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



/**
 * Objeto Processor para poder configurar cada template con un processor distinto
 * @author Guillermo Meyer
 */
public class Processor {
    //~ Instance fields ----------------------------------------------------------------------------

    private String type = null;
    private String id=null;

	public Processor() {
		super();
	}
	
	/**
	 * Creates a processor with a Class as a template processor 
	 * @param templateProcessor
	 */
	public Processor(Class templateProcessor) {
		this();
		this.setType(templateProcessor.getName());  
	}

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * @param string
     */
    public void setType(String string) {
        type = string;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
}
