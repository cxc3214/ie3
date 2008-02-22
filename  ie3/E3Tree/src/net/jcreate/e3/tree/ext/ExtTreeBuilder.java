package net.jcreate.e3.tree.ext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.e3.templateEngine.support.StrTemplateUtil;
import net.jcreate.e3.tree.BuildTreeException;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.support.AbstractWebTreeBuilder;
import net.jcreate.e3.tree.support.HttpServletRequestWebContext;
import net.jcreate.e3.tree.support.RequestUtil;
import net.jcreate.e3.tree.support.WebContext;
import net.jcreate.e3.tree.support.WebTreeNode;
import net.jcreate.e3.tree.xtree.XTreeBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExtTreeBuilder extends AbstractWebTreeBuilder{
	
	private final Log log = LogFactory.getLog(XTreeBuilder.class);
	
	public static final String DEFAULT_BEHAVIOR = "classic";
	/**
	 * xtree.css文件,要是完整路径
	 */
	private String style;
	
	
	private String title = "";

	private String resourceHome;
	
	public static final String DEFAULT_TREE_ID = "tree";
	public static final String DEFAULT_ROOT_NAME = "root"; 
	private String rootName = DEFAULT_ROOT_NAME;
	
	private String treeID = DEFAULT_TREE_ID;
	private boolean createDiv = true; 
	
    private boolean autoScroll = true;
    private boolean animate = true;
    private boolean enableDD = true;
    private boolean containerScroll = true;
    //是否显示border
    private boolean border = false;
    //似乎否显示根节点
    private boolean rootVisible = true;
    
    /**
     * 用于保存构造树用到的变量名.
     */
    protected List vars = null;
    
    /**
     * 是否显示节点之间的连线
     */
    private boolean lines = true;
    
    /**
     * 跟节点是否展开
     */
    private boolean rootExpand = true;
     
    /**
     * key是图标， value是样式名称.
     */
    private Map icon2styles = null;   
    private int iconCount = 0;
    
    static class Icon{
    	String icon;
    	String openIcon;
    	
    	public Icon(String pIcon, String pOpenIcon){
    		this.icon = pIcon;
    		this.openIcon = pOpenIcon;
    	}

    	//是否相等
		public boolean equals(Object obj) {
			if ( obj instanceof Icon == false ){
				return false;
			}
			Icon iconObj = (Icon)obj;
			String thisIdStr = String.valueOf(this.icon) + SPLITER + String.valueOf(this.openIcon);
			String thatIdStr = String.valueOf(iconObj.icon) + SPLITER + String.valueOf(iconObj.openIcon);
			return thisIdStr.equals(thatIdStr);
		}
    	private static final String SPLITER = "_$E3_TREE_ICON_PATH_SPLITER_-.#$$_";
    	
    	public int hashCode(){
    		String thisIdStr = String.valueOf(this.icon) + SPLITER + String.valueOf(this.openIcon);
    		return thisIdStr.hashCode();
    	}
    }
    
    private static final String COUNT_KEY = ExtTreeBuilder.class.getName() + ".e3.count";
    public void init(WebContext webContext) {
		super.init(webContext);
    	this.vars = new ArrayList();
    	icon2styles = new HashMap();
    	//将counter保存到page变量中，这样当一个页面有多棵树时，图标不会冲突.
    	Object countObj = webContext.getPageAttribute(COUNT_KEY);
    	if ( countObj == null ){
    		iconCount = 0;	
    		webContext.setPageAttribute(COUNT_KEY, new Integer(iconCount));
    	} else {
    		iconCount = ((Integer)countObj).intValue() + 1;
    	}
    	
	}

	public void init(HttpServletRequest pRequest){
		init(new HttpServletRequestWebContext(pRequest));
    }
	
    /**
     * 变量定义的占位符，最终生成script的时候需要把这个变量替换成 变量声明的代码.
     * 如果，这个关键子跟生成代码的字符串存在冲突时，可以覆盖该方法.
     * @return
     */
    protected String getVarSectionName(){
      return "_$SCRIPT_VARS$_";	
    }
    
    protected String getIconStyleSectionName(){
    	return "_$ICON_STYLE$_";
    }
	
	/**
	 * 负责导入Tree所需要的js,css
	 */
	public void buildTreeStart() throws BuildTreeException {
		StringBuffer resouces = new StringBuffer();
		resouces.append("<link type='text/css' rel='stylesheet' href='${style}' />").append(ENTER);		
		resouces.append("<script src='${resouceHome}/adapter/ext/ext-base.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/ext-all.js'></script>").append(ENTER);
		resouces.append(getIconStyleSectionName());
		
		if ( createDiv ){
			//如果这个div样式不符合标准，可以设置createDiv为false,用户自己定义
			resouces.append("<div id=\"${treeID}\" style=\"overflow:auto; height:100%;width:100%;\" />").append(ENTER);			
		}
		
		resouces.append("<script language='javascript'>").append(ENTER);
		resouces.append(getVarSectionName()).append(ENTER);
		resouces.append("Ext.onReady(function(){").append(ENTER);
		resouces.append("Ext.SSL_SECURE_URL= '${resouceHome}/resources/images/default/s.gif';").append(ENTER); 
		resouces.append("Ext.BLANK_IMAGE_URL= '${resouceHome}/resources/images/default/s.gif';").append(ENTER);
		
		resouces.append("${treeID} = new Ext.tree.TreePanel({").append(ENTER);
		resouces.append("       el:'${treeID}',").append(ENTER);
		resouces.append("       autoScroll:${autoScroll},").append(ENTER);
		resouces.append("       animate:${animate},").append(ENTER);
		resouces.append("       enableDD:${enableDD},").append(ENTER);
		resouces.append("       lines:${lines},").append(ENTER);
		resouces.append("       rootVisible:${rootVisible},").append(ENTER);		
		resouces.append("       title:'${title}',").append(ENTER);
		resouces.append("       border: ${border},").append(ENTER);		
	    resouces.append("       containerScroll: ${containerScroll}").append(ENTER);
	    resouces.append("     });").append(ENTER);
	    vars.add(this.treeID);
	    
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("style", getStyle());
		context.put("treeID", this.treeID);
		context.put("title", this.title);
		context.put("autoScroll", this.autoScroll);
		context.put("animate", this.animate);
		context.put("enableDD", this.enableDD);
		context.put("border", this.border);
		context.put("lines", this.isLines());		
		context.put("containerScroll", this.containerScroll);
		context.put("rootVisible", this.rootVisible);
		
	    
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pRootNode instanceof WebTreeNode == false){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode type");
		}

		WebTreeNode webTreeNode = (WebTreeNode)pRootNode;
		StringBuffer nodeTemplate = new StringBuffer();
		
		nodeTemplate.append("${nodeScriptName} = new Ext.tree.TreeNode({").append(ENTER);
		nodeTemplate.append("       id:'${nodeScriptName}', ").append(ENTER);
		nodeTemplate.append("       text:'${text}',").append(ENTER);
		nodeTemplate.append("       href:\"${action}\",").append(ENTER);	
		nodeTemplate.append("       hrefTarget:'${target}',").append(ENTER);		
		nodeTemplate.append("       qtip :'${tip}',").append(ENTER);
		nodeTemplate.append("       disabled: ${disabled},").append(ENTER);
		nodeTemplate.append("       allowDrag:${allowDrag},").append(ENTER);
		nodeTemplate.append("       allowDrop:${allowDrop},").append(ENTER);
		nodeTemplate.append("       ${userAttributes}");//节点扩展属性		
		nodeTemplate.append("       iconCls :'${iconCls}'").append(ENTER);		
		nodeTemplate.append("     });").append(ENTER);
		
		nodeTemplate.append("${rootName} = ${nodeScriptName} ; ").append(ENTER);
		nodeTemplate.append("${treeID}.setRootNode( ${rootName} );").append(ENTER);
		
		Context context = new DefaultContext(); 
		String nodeScriptName = getNodeScriptName(webTreeNode);
		context.put("nodeScriptName", nodeScriptName);
		context.put("userAttributes", getUserAttributes(webTreeNode));
		vars.add(nodeScriptName);
		vars.add(this.getRootName());
		
		
		context.put("text", webTreeNode.getName());
		context.put("action", webTreeNode.getAction());	
		context.put("treeID", this.treeID);
		context.put("rootName", this.getRootName());
		
		context.put("iconCls", getStyle( new Icon(webTreeNode.getIcon(), webTreeNode.getOpenIcon())));
		
		context.put("target", webTreeNode.getTarget());
		context.put("allowDrag", webTreeNode.isDragable());
		context.put("disabled", webTreeNode.isDisabled());
		context.put("allowDrop", webTreeNode.isDropable());
		context.put("tip", webTreeNode.getTip());
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));		
	}
	
	String getStyle(Icon pIcon){
		String result = (String)icon2styles.get(pIcon);
		if ( result  == null ){//不存在
			this.iconCount++;
			result = STYLE_PREFIX + iconCount;
			icon2styles.put(pIcon,STYLE_PREFIX + iconCount);
		}
		return result;
	}
	public static final String STYLE_PREFIX = "E3-TREE-STYLE-PREFIX"; 
	
	
	
		
	public void buildTreeEnd() throws BuildTreeException {
		StringBuffer temp = new StringBuffer();
		temp.append("if ( typeof(E3TreeExtReadyHandler) == 'function' )").append(ENTER);
		temp.append("  E3TreeExtReadyHandler(${treeID}, '${treeID}');").append(ENTER);
		temp.append("${treeID}.render();").append(ENTER);
	    if ( rootExpand ){
	    	temp.append("${rootName}.expand();").append(ENTER);
	    }
	    temp.append("});").append(ENTER);
		temp.append("</script>").append(ENTER);
		Context context = new DefaultContext(); 
		context.put("treeID", this.getTreeID());
		context.put("rootName", this.getRootName());
		treeScript.append(StrTemplateUtil.merge(temp.toString(), context));		   
	}
	

	//获取用户 
	final protected String getUserAttributes(WebTreeNode pNode){
		Map userAttributes = pNode.getUserAttributes();
		Iterator keyIterator = userAttributes.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while( keyIterator.hasNext() ){
			String key = (String)keyIterator.next();
			String value = (String)userAttributes.get(key);
			/**
			 * TODO: value进行escape处理，消除',"
			 */
			sb.append(key).append(" : ").append("'").append(value).append("'").append(",").append(ENTER);
		}
		return sb.toString();
	}
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		 
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		
		
		StringBuffer nodeTemplate = new StringBuffer();
	    
		nodeTemplate.append("${nodeScriptName} = new Ext.tree.TreeNode({").append(ENTER);
		nodeTemplate.append("       id:'${nodeScriptName}', ").append(ENTER);
		nodeTemplate.append("       text:'${text}',").append(ENTER);	 
		nodeTemplate.append("       href:\"${action}\",").append(ENTER);
		nodeTemplate.append("       hrefTarget:'${target}',").append(ENTER);		
		nodeTemplate.append("       qtip :'${tip}',").append(ENTER);	
		nodeTemplate.append("       disabled:${disabled},").append(ENTER);		
		nodeTemplate.append("       allowDrag:${allowDrag},").append(ENTER);
		nodeTemplate.append("       allowDrop:${allowDrop},").append(ENTER);
		nodeTemplate.append("       ${userAttributes}");//节点扩展属性
		nodeTemplate.append("       iconCls:'${iconCls}'").append(ENTER);
		nodeTemplate.append("     });").append(ENTER);
		nodeTemplate.append("${parentNodeScriptName}.appendChild( ${nodeScriptName} );").append(ENTER);		
	    

		Context context = new DefaultContext(); 
		String nodeScriptName = getNodeScriptName(webTreeNode);
		context.put("nodeScriptName", nodeScriptName);
		vars.add(nodeScriptName);
		context.put("parentNodeScriptName", parentNodeScriptName);
		context.put("text", webTreeNode.getName());
		context.put("userAttributes", getUserAttributes(webTreeNode));
		context.put("action", webTreeNode.getAction());
		context.put("iconCls", getStyle( new Icon(webTreeNode.getIcon(), webTreeNode.getOpenIcon())));		
		context.put("target", webTreeNode.getTarget());
		context.put("disabled", webTreeNode.isDisabled());
		context.put("allowDrag", webTreeNode.isDragable());
		context.put("allowDrop", webTreeNode.isDropable());
		context.put("tip", webTreeNode.getTip());
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
		
	}

	private String getVarScript(){
		StringBuffer sb = new StringBuffer();		
		sb.append("var ");
		int len = vars.size();
		for(int i=0; i<vars.size(); i++){
			String var = (String)vars.get(i);
			if ( i == (len-1) ){//最后一个
				sb.append(var).append(" ; ");	
			} else {
				sb.append(var).append(" , ");
			}			
		}
		return sb.toString();
	}
	
	private String getStyleScript(){
		Iterator nodeIterator = this.icon2styles.keySet().iterator();
		StringBuffer styleBS = new StringBuffer();
		styleBS.append("<style>").append(ENTER);
		while( nodeIterator.hasNext() ){
			StringBuffer nodeTemplate = new StringBuffer();
			Icon icon = (Icon)nodeIterator.next();
			String iconPath = icon.icon;
			String openIconPath = icon.openIcon;
			String style = (String)this.icon2styles.get(icon);
			if ( isEmpty(iconPath) == false ){
				nodeTemplate.append(".x-tree-node-collapsed .${style}  {").append(ENTER);
				nodeTemplate.append("background-image:url(${iconPath}) !important;").append(ENTER);
				nodeTemplate.append("}").append(ENTER);
				nodeTemplate.append(".x-tree-node-leaf .${style} {").append(ENTER);
				nodeTemplate.append("background-image:url(${iconPath}) !important;").append(ENTER);
				nodeTemplate.append("}").append(ENTER);
				
				
			}
			if ( isEmpty(openIconPath) == false ){
				nodeTemplate.append(".x-tree-node-expanded .${style}{").append(ENTER);
				nodeTemplate.append("background-image:url(${openIconPath}) !important;").append(ENTER);
				nodeTemplate.append("}").append(ENTER);
			}
			Context context = new DefaultContext();
			context.put("style", style);
			context.put("iconPath", iconPath);
			context.put("openIconPath", openIconPath);
			styleBS.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));			
		}
		styleBS.append("</style>").append(ENTER);		
		return styleBS.toString();
		
	}
	public final String getTreeScript(){
		String varSec = this.getVarSectionName();
		String varScript = getVarScript();
		int index = treeScript.indexOf(varSec);		
		int end = varSec.length();
		treeScript.replace(index, index + end, varScript);
		
		String styleSec =getIconStyleSectionName();
		String styleScript = this.getStyleScript();
		index = treeScript.indexOf(styleSec);		
		end = styleSec.length();
		treeScript.replace(index, index + end, styleScript);
		return super.getTreeScript();
	}
	

	public String getResourceHome() {
		if ( resourceHome == null ){
			return this.webContext.getContextPath() + "/e3/tree/ext";
		} else {
			return resourceHome;
		}
	}

	public void setResourceHome(String resourceHome) {
		this.resourceHome = resourceHome;
	}


	public String getStyle() {
		if ( style == null ){
			return getResourceHome() + "/resources/css/ext-all.css";
		}
		return style;
	}

	public void setStyle(String pStyle) {
		this.style = pStyle;
	}


	public String getTreeID() {
		return treeID;
	}

	public void setTreeID(String treeID) {
		this.treeID = treeID;
	}

	public boolean isCreateDive() {
		return createDiv;
	}

	public void setCreateDive(boolean createDive) {
		this.createDiv = createDive;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public boolean isRootVisible() {
		return rootVisible;
	}

	public void setRootVisible(boolean rootVisible) {
		this.rootVisible = rootVisible;
	}

	public boolean isRootExpand() {
		return rootExpand;
	}

	public void setRootExpand(boolean rootExpand) {
		this.rootExpand = rootExpand;
	}

	public boolean isLines() {
		return lines;
	}

	public void setLines(boolean lines) {
		this.lines = lines;
	}
	public static void main(String[] args){
		StringBuffer sb = new StringBuffer();
		sb.append("aab");
		sb.replace(0,2,"xxddd");
		System.out.println(sb.toString());
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}


}

