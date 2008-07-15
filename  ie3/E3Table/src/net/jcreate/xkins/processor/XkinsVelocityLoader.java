package net.jcreate.xkins.processor;

import java.io.InputStream;

import net.jcreate.xkins.ContextHolder;
import net.jcreate.xkins.Skin;
import net.jcreate.xkins.Template;
import net.jcreate.xkins.Xkins;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

/**
 * 说明：本项目来源 http://xkins.sourceforge.net/ 
 * 负责装载模板定义
 * @author 黄云辉
 *
 */
public class XkinsVelocityLoader extends ResourceLoader {
	
	public XkinsVelocityLoader() {
	}
		
	public void init(ExtendedProperties arg0) {
	}

	public InputStream getResourceStream(String name) throws ResourceNotFoundException {
		Template tmp = this.getTemplate(name);
		return tmp.getContent();
	}

	public boolean isSourceModified(Resource arg0) {
		boolean ret =this.getXkins().isTemplateModified(arg0.getName()); 	
		return ret;
	}

	public long getLastModified(Resource arg0) {
		return 0;
	}

	private Skin getSkin(String key) throws ResourceNotFoundException {
		String skinName = key.substring(0, key.indexOf('.'));
		Skin skin = this.getXkins().getSkin(skinName);
		if(skin==null)
			throw new ResourceNotFoundException("不存在皮肤: " + skinName);
		return skin;
	}

	private Template getTemplate(String key) throws ResourceNotFoundException {
		String templateName = key.substring(key.indexOf('.') + 1, key.length());
		Skin skin = this.getSkin(key);
		Template tmp = skin.getTemplate(templateName);
		if(tmp==null)
			throw new ResourceNotFoundException("不存在指定模板： " + key);

		return tmp;
	}
	
	
	/**
	 * @return
	 */
	public Xkins getXkins() {
		return ContextHolder.getContext().getXkins();
	}

}
