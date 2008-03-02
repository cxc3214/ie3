/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)XkinsException.java
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
 * Exception para checked Exceptions
 * @author Guillermo Meyer
 */
public class XkinsException
        extends Exception {
    //~ Constructors -------------------------------------------------------------------------------

    /**
     *
     */
    public XkinsException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public XkinsException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public XkinsException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public XkinsException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
