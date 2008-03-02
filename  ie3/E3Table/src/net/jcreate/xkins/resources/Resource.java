/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)Resource.java
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
import net.jcreate.xkins.Skin;
import net.jcreate.xkins.Template;

/**
 * Un recurso es algo utilizable dentro del Skin. "Algo" significa un elemento (im�gen, archivo css, html, etc) o
 * una constante. Esto permite estructurar el Skin en recursos para poder reusarlos y facilitar el mantenimiento y
 * la herencia.<br>
 * Los recursos pueden pertenecer a un Skin o a un template. Los recursos declarados a nivel Skin pueden ser accedidos
 * desde cualquier template. Los recursos asociados un template puede ser accedido desde el tempalte o desde otro
 * tempalte si se indica el nombre del mismo en el atributo <code>srcTemplate</code>.
 * @author Guillermo Meyer
 * @see net.jcreate.xkins.resources.ElementResource
 * @see net.jcreate.xkins.resources.ConstantResource
 */
public abstract class Resource
        implements Cloneable {
    //~ Instance fields ----------------------------------------------------------------------------

    protected Skin skin = null;
    protected String name = null;
    protected Template template = null;
    private String srcTemplateName = null; //template del que se debe tomar el resource  

    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Resource() {
    }

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Este m�todo debe ser implementado para ejecutar la forma del recurso. Por ejemplo, una constante implementar�a
     * devolver el valor directamente, o un recurso del tipo i18n podr�a devolver la internacionalizaci�n de un
     * key indicado en el valor.
     * @param context
     * @return
     */
    public abstract String getData();

    /**
     * Asigna el nombre del recurso.
     * @param s
     */
    public void setName(String s) {
        this.name = s;
    }

    /**
     * Devuelve el nombre del recurso.
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Asigna el skin al que pertenece el recurso.
     * @param skin The skin to set
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    /**
     * Devuelve el Skin al que pertenece el recurso.
     * @return Returns a Skin
     */
    public Skin getSkin() {
        return skin;
    }

    /**
     * Asigna el nombre del template al cual pertenece el recurso.
     * @param srcTemplate The srcTemplate to set
     */
    public void setSrcTemplate(String srcTemplateName) {
        this.srcTemplateName = srcTemplateName;
    }

    /**
     * Devuelve el nombre del template del cual se debe tomar el recurso. Permite indirecci�n de recursos.
     * @return Returns a Template
     */
    public String getSrcTemplate() {
        return srcTemplateName;
    }

    /**
     * Asigna el tempalte al que pertenece el resource.
     * @param template
     */
    public void setTemplate(Template template) {
        this.template = template;
        if (template != null) {
            this.skin = template.getSkin();
        }
    }

    /**
     * Devuelve el tempalte al que pertenece el resource.
     * @return
     */
    public Template getTemplate() {
        return this.template;
    }

    /**
     * Clona el recurso. Llama al super.clone();
     */
    public Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Devuelve el String con el HTML para mostrar el resource.
     * @return
     */
    public String printHTML() {
        return this.name + "(" + this.getData() + ")";
    }

    /**
     * el toString es el nombre.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Obtiene el recurso de otro template utilizando el nombre definido en stcTemplate.
     * @param ctx
     * @return
     */
    protected String getFromOtherTemplate(Context ctx) {
        if (this.getSrcTemplate() != null) {
            if (this.getTemplate() != null) {
                return this.getTemplate().getSkin().getTemplate(this.getSrcTemplate())
                           .getResource(this.name).getData();
            } else {
                return this.getSkin().getTemplate(this.getSrcTemplate()).getResource(this.name)
                           .getData();
            }
        } else {
            return null;
        }
    }
}
