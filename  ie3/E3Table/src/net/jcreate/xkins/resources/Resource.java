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

public abstract class Resource
        implements Cloneable {
    //~ Instance fields ----------------------------------------------------------------------------

    protected Skin skin = null;
    protected String name = null;
    protected Template template = null;
    private String srcTemplateName = null; 
    private boolean dynamic = false;
    private boolean exeTwice = false;
                       
    

    //~ Constructors -------------------------------------------------------------------------------

    public boolean isDynamic() {
		return dynamic;
	}

	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	/**
     * Constructor por defecto.
     */
    public Resource() {
    }

    //~ Methods ------------------------------------------------------------------------------------

    public abstract String getData();

    /**
     * 设置资源名字
     * @param s
     */
    public void setName(String s) {
        this.name = s;
    }

    /**
     * 获取资源名字
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置皮肤
     * @param skin
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    /**
     * 获取皮肤
     * @return
     */
    public Skin getSkin() {
        return skin;
    }

    public void setSrcTemplate(String srcTemplateName) {
        this.srcTemplateName = srcTemplateName;
    }

    public String getSrcTemplate() {
        return srcTemplateName;
    }

    public void setTemplate(Template template) {
        this.template = template;
        if (template != null) {
            this.skin = template.getSkin();
        }
    }

    public Template getTemplate() {
        return this.template;
    }

    public Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

    public String printHTML() {
        return this.name + "(" + this.getData() + ")";
    }

    public String toString() {
        return this.name;
    }

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

	public boolean isExeTwice() {
		return exeTwice;
	}

	public void setExeTwice(boolean exeTwice) {
		this.exeTwice = exeTwice;
	}
}
