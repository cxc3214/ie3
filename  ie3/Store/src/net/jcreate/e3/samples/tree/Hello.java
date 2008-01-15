package net.jcreate.e3.samples.tree;

import java.io.StringWriter;

import net.jcreate.e3.templateEngine.Template;
import net.jcreate.e3.templateEngine.TemplateEngine;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.e3.templateEngine.support.DefaultTemplate;
import net.jcreate.e3.templateEngine.support.TemplateEngineFactory;
import net.jcreate.e3.templateEngine.support.TemplateType;

public class Hello {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Template template = new DefaultTemplate();
		template.setResource(DefaultTemplate.STR_PREFIX + "hello ${msg}");
		StringWriter out = new StringWriter();
		TemplateEngine engine = TemplateEngineFactory.getInstance(TemplateType.FREE_MARKER);
		engine.mergeTemplate(template, new DefaultContext(), out);

	}

}
