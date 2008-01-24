package net.jcreate.e3.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

public class E3BeanFactoryBuilder {
	
      private E3BeanFactoryBuilder(){
    	  
      }
      
 	  public static BeanFactory build(String[] pBeansConfigFiles)throws BeansException{
		  return new E3ClassPathXmlApplicationContext(pBeansConfigFiles);
	  }
	   
	  public static BeanFactory build(String pBeansConfigFile)throws BeansException{
		    return new E3ClassPathXmlApplicationContext(new String[]{pBeansConfigFile});
	  }

}
