/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)ElementResource.java
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

import net.jcreate.xkins.Context;
import net.jcreate.xkins.ContextHolder;
import net.jcreate.xkins.Path;
import net.jcreate.xkins.XkinsRuntimeException;

/**
 * El ElementResource permite generar elementos en los skins. Por elementos se entiende a im�genes, archivos,
 * etc., todo aquello ubicable mediante una URL.
 * Este recurso requiere que se le informe un Path y una url. El recurso final queda conformado, al procesarlo por
 * <code>el contexto de la aplicaci�n + la URL del skin actual + la url del path + la url del elemento</code>.
 * Al igual que la constante y cualquier recurso, puede ser sobreescrito por otro skin que herede al skin contenedor
 * del ElementoResource para modificar su valor.
 * Por ejemplo:<br>
 * <ul>
 * <li>Skin: la URL del skin es "/skins/default"</li>
 * <li>Path: la URL del path es "/imagenes"</li>
 * <li>Element: la URL del element es "/img.gif" y est� asociado al path anterior.
 * </ul>
 * Si el contexto de la aplicaci�n es "/app", el resultado de procesar el element definido arriba es:<br>
 *
 * <code>/app/skins/default/imagenes/img.gif"</code><br>
 *
 * @see net.jcreate.xkins.resources.ConstantResource
 * @see net.jcreate.xkins.resources.Resource
 * @author guillermo Meyer
 */
public class ElementResource
        extends Resource {
    //~ Instance fields ----------------------------------------------------------------------------

    private String strPath = null;
    private String url = null;

    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public ElementResource() {
        super();
    }

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Procesa la informaci�n. Genera el string representando la URL completa desde el ContextPath obtenido
     * desde el context, la URL del skin, la URL del path y la URL del element.
     */
    public String getData() {
		Context ctx = ContextHolder.getContext();
        String aux = getFromOtherTemplate(ctx);
        if (aux != null) {
            return aux;
        } else {
            return this.getSkinPath().getFullUrl() + this.getUrl();
        }
    }

    /**
     * Asigna el path del resource.
     * @param s
     */
    public void setPath(String s) {
        this.strPath = s;
    }

    /**
     * Obtiene el path del resource.
     * @return
     */
    public String getPath() {
        return this.strPath;
    }

    /**
     * Devuelve el path del skin al que pertenece el resource.
     * @return
     */
    public Path getSkinPath() {
    	Path path=null;
        if (this.getTemplate() != null) {
        	path=this.getTemplate().getSkin().getPath(this.strPath);
        } else {
        	path=this.getSkin().getPath(this.strPath);
        }
		if(path==null)
			throw new XkinsRuntimeException("El path no esta definido: " + this.strPath);

		return path;
    }

    /**
     * Asigna la URL del elemento.
     * @param s
     */
    public void setUrl(String s) {
        this.url = s;
    }

    /**
     * Devuelve la URL del element.
     * @return
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Llama al super.clone()
     */
    public Object clone()
            throws CloneNotSupportedException {
        Object obj = super.clone();
        return obj;
    }
}
