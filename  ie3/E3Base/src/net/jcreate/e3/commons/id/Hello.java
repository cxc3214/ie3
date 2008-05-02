package net.jcreate.e3.commons.id;


public class Hello {
public static void main(String[] args){
	DefaultIDGenerator generator = new DefaultIDGenerator();
	DefaultPrefixGenerator prefixGenerator = new DefaultPrefixGenerator();
	prefixGenerator.setWithDate(true);
	generator.setPrefixGenerator(prefixGenerator);
	DefaultSequenceGenerator sequenceGenerator = new DefaultSequenceGenerator();
	sequenceGenerator.setMinValue(10);
	sequenceGenerator.setMaxValue(20);
	sequenceGenerator.setCycle(true);
	MemorySequenceStorer sequenceStore = new MemorySequenceStorer();
	sequenceGenerator.setSequenceStorer(sequenceStore);
	generator.setSequenceGenerator(sequenceGenerator);
	for(int i=0; i<22; i++){
	  final String id = generator.create();
  	  System.out.println(id);
	}
	java.util.Random a;
}
}
