package net.jcreate.e3.commons.id.util;

import net.jcreate.e3.commons.id.fomater.DefaultSequenceFormater;
import net.jcreate.e3.commons.id.generator.DefaultIDGenerator;
import net.jcreate.e3.commons.id.generator.UUIDGenerator;
import net.jcreate.e3.commons.id.prefix.DefaultPrefixGenerator;
import net.jcreate.e3.commons.id.sequence.DefaultSequenceGenerator;

public abstract class IDHelper {
	
	private static final UUIDGenerator uuidGenerator = new UUIDGenerator();
	
	private static final DefaultIDGenerator e3idGenerator = new DefaultIDGenerator();
	
	static{
		
		DefaultPrefixGenerator prefixGenerator = new DefaultPrefixGenerator();
		prefixGenerator.setWithDate(true);
		e3idGenerator.setPrefixGenerator(prefixGenerator);
		
		//序号生成器
		DefaultSequenceGenerator sequenceGenerator = new DefaultSequenceGenerator("net-jcreate-e3-id");
		sequenceGenerator.setMinValue(0);
		sequenceGenerator.setMaxValue(999999999999L);
		sequenceGenerator.setCycle(true);
		sequenceGenerator.setCache(1000);
		e3idGenerator.setSequenceGenerator(sequenceGenerator);
		
		DefaultSequenceFormater sequenceFormater = new DefaultSequenceFormater();
		sequenceFormater.setPattern("000000000000");
		e3idGenerator.setSequenceFormater(sequenceFormater);
	}
	
  private IDHelper(){
  }
  
  public static String uuid(){
	  return uuidGenerator.create();
  }
  
  public static String e3id(){
	  return e3idGenerator.create();
  }
  
  public static void main(String[] args){
	  for(int i=0; i<100; i++){
		  System.out.println( IDHelper.e3id());	  
	  }
	  
  }
  
  
}
