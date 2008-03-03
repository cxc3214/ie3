/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)ConstantResource.java
 *
 * Permission is granted to copy, distribute and/or modify this document
 * under the terms of the GNU Free Documentation License, Version 1.1 or
 * any later version published by the Free Software Foundation; with no
 * Invariant Sections, with no Front-Cover Texts.
 * A copy of the license is included in the section entitled
 * "GNU Free Documentation License".
 * ====================================================================
 */
package net.jcreate.xkins.resources;

import net.jcreate.xkins.ContextHolder;


/**
 * Esta implementaci�n de Resource permite el uso de constantes dentro de los skins.<br>
 * La constante puede contener, por ejemplo, el color de una celda. Los templates pueden hacer uso de esta
 * constante sin usar el color expl�citamente. Si un skin extiende a otro que posee una constante, puede
 * sobreescribir la constante para cambiar el color de la celda, usar el mismo template pero verse distinto.
 * @author Guillermo Meyer
 * @see net.jcreate.xkins.resources.ElementResource
 * @see net.jcreate.xkins.resources.Resource
 */
public class ConstantResource
        extends Resource {
    //~ Instance fields ----------------------------------------------------------------------------

    private String value = "";
    private String type = "string";

    //~ Constructors -------------------------------------------------------------------------------

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
     * Constructor por defecto.
     */
    public ConstantResource() {
        super();
    }

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Implementaci�n del getData abstracto. Devuelve el valor declarado en la property value.
     */
    public String getData() {
        String aux = getFromOtherTemplate(ContextHolder.getLocalContext());
        if (aux != null) {
            return aux;
        } else {
            return this.value;
        }
    }
    

    /**
     * Asigna el value de la constante.
     * @param value The value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Devuelve el value.
     * @return Returns a String
     */
    public String getValue() {
        return value;
    }
}
