package net.jcreate.store.template;

public class NotExistTemplateVarException extends RuntimeException {
	
	public NotExistTemplateVarException(){
		super("不存在模板变量异常.");
	}
	
	public NotExistTemplateVarException(String pMsg){
		super(pMsg);
	}
	

}
