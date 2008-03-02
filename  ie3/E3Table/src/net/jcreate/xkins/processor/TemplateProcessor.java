/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)TemplateProcessor.java
 *
 * Permission is granted to copy, distribute and/or modify this document
 * under the terms of the GNU Free Documentation License, Version 1.1 or
 * any later version published by the Free Software Foundation; with no
 * Invariant Sections, with no Front-Cover Texts.
 * A copy of the license is included in the section entitled
 * "GNU Free Documentation License".
 * ====================================================================
 */
package net.jcreate.xkins.processor;

import java.io.StringWriter;

import net.jcreate.xkins.Context;
import net.jcreate.xkins.Template;
import net.jcreate.xkins.Xkins;
import net.jcreate.xkins.XkinsException;


/**
 * La interfaz TemplateProcessor permite modificar los procesadores de tempaltes. Los procesadores de templates deben
 * implementar esta interfaz. Es este tipo de objetos los que utiliza el XkinProcessor para procesar los templates.
 * @author Guillermo Meyer
 * @see net.jcreate.xkins.XkinProcessor
 */
public interface TemplateProcessor {
    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Procesa el template indicado en input, utilizando el context como par�metro.
     * Cada implementaci�n debe tratar el tempalte y escribir en el outputstream con el resultado del procesamiento.<br>
     * @param input
     * @param context
     * @throws XkinsException
     */
    void process(Template input, Context context, StringWriter pWriter)
            throws XkinsException;
    
    /**
     * Inicializa el processor con un template
     * @param input
     * @throws XkinsException
     */
    void init(Xkins skins) throws XkinsException;
}