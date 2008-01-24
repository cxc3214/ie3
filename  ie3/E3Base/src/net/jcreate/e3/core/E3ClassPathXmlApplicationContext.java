package net.jcreate.e3.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class E3ClassPathXmlApplicationContext extends ClassPathXmlApplicationContext{


	public E3ClassPathXmlApplicationContext(String arg0) throws BeansException {
		super(arg0);
	}

	public E3ClassPathXmlApplicationContext(String[] arg0, ApplicationContext arg1) throws BeansException {
		super(arg0, arg1);
	}

	public E3ClassPathXmlApplicationContext(String[] arg0, boolean arg1, ApplicationContext arg2) throws BeansException {
		super(arg0, arg1, arg2);
	}

	public E3ClassPathXmlApplicationContext(String[] arg0, boolean arg1) throws BeansException {
		super(arg0, arg1);
	}

	public E3ClassPathXmlApplicationContext(String[] arg0) throws BeansException {
		super(arg0);
	}
	
	public Resource getResource(String location) {
		Assert.notNull(location, "location is required");
		E3ResourceLoader resourceLoader = new E3ResourceLoader();
		return resourceLoader.getResource(location);
   }
	
	
}
