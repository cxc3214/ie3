package net.jcreate.xkins.demo;

import net.jcreate.xkins.Skin;
import net.jcreate.xkins.Template;
import net.jcreate.xkins.XkinProcessor;
import net.jcreate.xkins.Xkins;
import net.jcreate.xkins.XkinsException;
import net.jcreate.xkins.XkinsLoader;

public class Test {
public static void main(String[] args){
	Xkins xks = null;
	try {
		xks = (new XkinsLoader()).loadSkins("/net/jcreate/xkins/demo/mail-templates.xml");
	} catch (XkinsException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Skin skin = xks.getSkin("default");
	//XkinProcessor.get
	XkinProcessor xkpBody = new XkinProcessor();
	Template tmpBody = skin.getTemplate("mail");
	xkpBody.setTemplate(tmpBody);
	xkpBody.addParameter("text", "This is a text");
	System.out.println(xkpBody.processContent());

}
}
