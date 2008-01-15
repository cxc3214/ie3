package net.jcreate.store.template;

import java.text.AttributedString;
import java.text.CharacterIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author 黄云辉
 * @version 1.0
 */

public class Template {

	private final Log log = LogFactory.getLog(Template.class);

	private TemplateContext context;

	private TemplateAction action;

	private int varIndex = 1; //当前处于分析状态的模板变量是第几个模板变量

	private String strMsg = null;//带模板变量的字符串

	private char pattern = TemplateVarPattern.PREPARED_STATEMENT_PATTERN;

	public Template() {
		context = new TemplateContext();
		action = new DefaultTemplateAction();
	}

	/**
	 * 合并模板变量
	 * @param pMsg 待需进行合并处理的字符串(包含模板变量)
	 * @return 合并后的结果
	 */
	public String merge(final String pMsg) {
		if (pMsg == null)
			return null;
		strMsg = pMsg;
		varIndex = 1;//合并的时候置初值
		int beginIndex = -1;//开始位置
		int endIndex = -1;//结束位置
		int intTemp = 0; //当值为2时表示发现一个模板变量
		String strTemp = null; //保存模式板变量
		StringBuffer result = new StringBuffer(20);//返回结果
		AttributedString str = new AttributedString(pMsg);
		CharacterIterator ci = str.getIterator();
		//这不进行回退处理，只要找到$,就认为一定存在模板变量
		for (char ch = ci.first(); ch != ci.DONE; ch = ci.next()) {
			if (ch == this.pattern) {//模板变量的开始符号
				if (intTemp == 1) {
					result.append(this.pattern);
				}
				intTemp = 1;
			} else if (ch == '{') {//只有近跟在$后面的{才是元字符
				if (intTemp == 1) {
					beginIndex = ci.getIndex() + 1;
					intTemp = 2;//找到开始符号
				} else {
					if (intTemp == 1)
						result.append(this.pattern);
					intTemp = 0;
					result.append(ch);
				}
			} else if (ch == '}') {
				if (intTemp == 2) {//
					endIndex = ci.getIndex();//找到一个模板变量
					strTemp = pMsg.substring(beginIndex, endIndex);
					result.append(getTemplateVarValue(strTemp));
				} else {
					if (intTemp == 1)
						result.append(this.pattern);
					result.append(ch);
				}
				intTemp = 0; //${只有连续出现时才视为元字符
			} else {
				if (intTemp != 2) {//没有找到开始符号
					if (intTemp == 1) {
						result.append(this.pattern);
						intTemp = 0;
					}
					result.append(ch);
				} else {
					if (ci.next() == ci.DONE) {
						result.append(pMsg.substring(beginIndex - 2));
					} else {
						ci.previous();
					}
				}
			}
		}
		return result.toString();

	}

	/**
	 * 获取模板变量的值
	 * @param pTemplateVar : 模板变量
	 * @param pContext     ：信息环境
	 * @return             ：模板变量的值，如果在信息环境中没有
	 *                       发现模板变量 pTemplateVar，则返回
	 *                       pTemplateVar
	 */
	private String getTemplateVarValue(final String pTemplateVar) {
		if (context.containsKey(pTemplateVar.trim()) == false){
			log.warn("模板变量:" + pTemplateVar + "不存在!");
			return String.valueOf(this.pattern) + "{" + pTemplateVar + "}";
		}
		ParseContext aParseContext = new ParseContext();
		aParseContext.setTemplateVarName(pTemplateVar.trim());
		aParseContext.setIndex(varIndex);
		aParseContext.setTemplateMsg(strMsg);
		varIndex++;
		return action.perform(aParseContext, context);
	}

	public TemplateContext getContext() {
		return context;
	}

	public void setContext(TemplateContext context) {
		this.context = context;
	}

	/**
	 * 设置对象
	 * @param pKey ：键
	 * @param pValue：值
	 */
	public void putTemplateVar(final String pKey, final Object pValue) {
		context.put(pKey, pValue);
	}

	public TemplateAction getAction() {
		return action;
	}

	public void setAction(TemplateAction action) {
		this.action = action;
	}

	public static void main(String[] args) {
		Template template = new Template();
		template.setPattern(TemplateVarPattern.STATEMENT_PATTERN);
		template.putTemplateVar("msg", "hello");
		template.putTemplateVar("d", "hello");
		System.out.println(template.merge("${msg} nihao! #{d}"));

	}
 
	public void setPattern(char pattern) {
		this.pattern = pattern;
	}

}
