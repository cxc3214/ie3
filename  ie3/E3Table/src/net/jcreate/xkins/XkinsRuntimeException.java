/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)XkinsRuntimeException.java
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
 * Exceptionpara problemas en tiempo de ejecuci�n.
 * @author Guillermo Meyer
 */
public class XkinsRuntimeException
        extends RuntimeException {
    //~ Constructors -------------------------------------------------------------------------------

    /**
     *
     */
    public XkinsRuntimeException() {
        super();
    }

    /**
     * @param message
     */
    public XkinsRuntimeException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public XkinsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public XkinsRuntimeException(Throwable cause) {
        super(cause);
    }
}
