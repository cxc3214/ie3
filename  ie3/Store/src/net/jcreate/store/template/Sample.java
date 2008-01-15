package net.jcreate.store.template;

public class Sample {
  public Sample() {
  }

  public static void main(String[] args) throws Exception {
    Template aTemplate = new Template();
    aTemplate.putTemplateVar("Author", null);
    aTemplate.putTemplateVar("Date", "2003-10-20");
    System.out.println(aTemplate.merge("${Author}是${Date}发财的,呵呵!"));
  }
}



