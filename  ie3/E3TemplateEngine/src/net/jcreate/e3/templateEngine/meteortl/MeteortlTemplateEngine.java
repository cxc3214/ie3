package net.jcreate.e3.templateEngine.meteortl;

import java.io.Writer;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.MergeTemplateException;
import net.jcreate.e3.templateEngine.Template;
import net.jcreate.e3.templateEngine.support.TemplateEngineSupport;
import net.jcreate.e3.templateEngine.velocity.VelocityHelper;

import org.meteortl.config.Configuration;
import org.meteortl.core.Factory;
import org.meteortl.standard.StandardConfiguration;

public class MeteortlTemplateEngine extends TemplateEngineSupport{
	private Configuration config = null; 
	private Factory factory = null;
	protected void mergeFileTemplate(Template pTemplate, Context pContext, Writer pWriter) throws MergeTemplateException {
		// TODO Auto-generated method stub
		
	} 

}
