package net.jcreate.e3.commons.id.test;

import net.jcreate.e3.commons.id.fomater.DefaultSequenceFormater;
import net.jcreate.e3.commons.id.generator.DefaultIDGenerator;
import net.jcreate.e3.commons.id.prefix.DefaultPrefixGenerator;
import net.jcreate.e3.commons.id.sequence.DefaultSequenceGenerator;


public class Hello {
public static void main(String[] args){
	//构造生成器
	DefaultIDGenerator generator = new DefaultIDGenerator();
	
	//前缀生成器，用于产生ID前缀
	DefaultPrefixGenerator prefixGenerator = new DefaultPrefixGenerator();
	prefixGenerator.setWithDate(false);
	generator.setPrefixGenerator(prefixGenerator);
	prefixGenerator.setPrefix("E3_");
	
	//序号生成器
	DefaultSequenceGenerator sequenceGenerator = new DefaultSequenceGenerator();
	sequenceGenerator.setMinValue(0);
	sequenceGenerator.setMaxValue(9999999);
	sequenceGenerator.setCycle(true);
	sequenceGenerator.setCache(100);
	generator.setSequenceGenerator(sequenceGenerator);
	
	//格式化序号
	DefaultSequenceFormater sequenceFormater = new DefaultSequenceFormater();
	sequenceFormater.setPattern("0000000");
	generator.setSequenceFormater(sequenceFormater);
 	System.out.println(generator.create());
 	System.out.println(generator.create());
}
	  
}
 