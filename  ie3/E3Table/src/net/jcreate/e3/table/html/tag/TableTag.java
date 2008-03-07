/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 欢迎加入 E3平台联盟QQ群:21523645 
 */
package net.jcreate.e3.table.html.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.SortInfo;
import net.jcreate.e3.table.StateInfo;
import net.jcreate.e3.table.TableDirector;
import net.jcreate.e3.table.html.AbstractHTMLTableBuilder;
import net.jcreate.e3.table.html.HTMLBuilderFactory;
import net.jcreate.e3.table.html.HTMLColumn;
import net.jcreate.e3.table.html.HTMLParam;
import net.jcreate.e3.table.html.HTMLRow;
import net.jcreate.e3.table.html.HTMLTable;
import net.jcreate.e3.table.html.HTMLTableCreator;
import net.jcreate.e3.table.html.HTMLTableHelper;
import net.jcreate.e3.table.html.SessionStateManager;
import net.jcreate.e3.table.i18n.I18nResourceProviderFactory;
import net.jcreate.e3.table.message.MessageSourceFactory;
import net.jcreate.e3.table.model.DataModelFactory;
import net.jcreate.e3.table.support.DefaultStateInfo;
import net.jcreate.e3.table.support.DefaultTableContext;
import net.jcreate.e3.table.support.DefaultTableDirector;
import net.jcreate.e3.table.support.JspPageWebContext;
import net.jcreate.e3.table.support.TableConstants;
import net.jcreate.e3.table.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class TableTag extends BodyTagSupport{
	
	private final Log logger = LogFactory.getLog( this.getClass() );
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否启用状态管理
	 */
	private boolean enabledStateManager = true;
	/**
	 * 状态参数，默认是_e3state,当指定参数值为true时，则从stateManager中回复table状态
	 */
	private String stateParam = TableConstants.DEFAULT_STATE;
	
	/**
	 * 表格ID
	 */
	private String id;
	
	/**
	 * 变量状态
	 */
	private String varStatus;
	
	/**
	 * 变量名，用于存储跌代对象
	 */
	private String var;
	/**
	 * NavResponse对象key,要求从key中能找到NavResponse值
	 */
	private String items;
	
	/**
	 * 每页显示记录数，默认显示20条
	 */
	private int pageSize = TableConstants.DEFAULT_PAGE_SIZE;

	/**
	 * 变量范围，有效值[page|request|session|application]
	 */
	private String scope;
	/**
	 * 譬如
	 */
	private String skin = TableConstants.DEFAULT_SKIN;
	/**
	 * 标题 
	 */
	private String caption;
	/**
	 * 标题key
	 */
	private String captionKey;
	/**
	 * 没有数据时的提示信息
	 */
	private String noDataTip;
	/**
	 * 没有数据说提示信息key
	 */
	private String noDataTipKey;
	
	
	/**
	 * 查询uri, 
	 */
	private String uri = "?";
	
	/**
	 * 当前行对象
	 */
	private HTMLRow currRow = null;
	
	/**
	 * builder，用于构造Table
	 */
	private String builder = TableConstants.DEFAULT_BUILDER;
	

	private HTMLTable table;
    /**
     * 是否创建了table对象
     */	
	private boolean createdTable = false;
	/**
	 * 是否创建了header对象
	 */
	private boolean createdHeader = false;
	
	/**
	 * table包含的列名 
	 */
	private ArrayList columnProperties = new ArrayList();
	
	private DataModel dataModel = null;
	
	private String i18n = TableConstants.DEFAULT_I18N;


	public String getI18n() {
		return i18n;
	}

	public void setI18n(String i18n) {
		this.i18n = i18n;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}


	/**
	 */
	public int doAfterBody() throws JspException {
		/**
		 * 第一次进来创建表格。
		 * 第2次已经创建了表头，进来设置控制标致createdHeader为true
		 * 从第2次开始就要检查是否需要循环处理.
		 */
		if ( this.createdTable == false ){//没创建表格，则进行表格构造
			HTMLTableCreator creator = new HTMLTableCreator();
			
			table = (HTMLTable)creator.createTable(dataModel, (String[])this.columnProperties.toArray(new String[this.columnProperties.size()]));
			table.setId(this.id);
			table.setSkin(this.skin);
			table.setCaption(this.caption);
			table.setUri(this.uri);
			table.setPageInfo(this.getNavInfo());
			table.setCaptionKey(this.captionKey);
			table.setNoDataTipKey(noDataTipKey);
			table.setNoDataTip(noDataTip);
			this.createdTable = true;
			return EVAL_BODY_AGAIN;
		}
		if ( this.createdHeader == false ){
			this.createdHeader = true;
		}
		boolean isHaveNext = (table.getRowSize() ) > rowIndex ;		
		if ( isHaveNext ){
			initData();
			return EVAL_BODY_AGAIN;
		}else{
			return SKIP_BODY;
		}
	}
	private int rowIndex = 0;
	/**
	 * 初始化数据
	 *
	 */
	private void initData(){
		if ( this.createdHeader ){//之后创建完表头，才可以进行数据循环处理
			this.currRow = (HTMLRow)table.getRow(rowIndex);
			if ( this.var != null ){
				this.pageContext.setAttribute(this.var, this.currRow.getRowObject());
			}
			if (this.varStatus != null ){
				if ( loopTagStatus == null ){
					loopTagStatus = new LoopTagStatus();
				}
				loopTagStatus.setCount(rowIndex+1);
				loopTagStatus.setIndex(rowIndex);
				loopTagStatus.setCurrent(this.currRow.getRowObject());
				loopTagStatus.setFirst(this.currRow.isFirst());
				loopTagStatus.setLast(this.currRow.isLast());
				loopTagStatus.setOdd(this.currRow.isOdd());
				this.pageContext.setAttribute(this.varStatus, loopTagStatus);
			}
			rowIndex++;
		}
	}

	public void addParam(HTMLParam pParam){
		if ( this.table != null ){
		    this.table.addParam(pParam);
		}
	}
	public SortInfo getSortInfo(){
	   return this.dataModel.getSortInfo();
		
	}
	public PageInfo getNavInfo(){
			return this.dataModel.getNavInfo();
	}
	
	public int doStartTag() throws JspException {		
		Object itemsObj = this.pageContext.findAttribute(this.items);
		//当items是 collection类型时，系统自动做特殊处理，这样就可以很方便实现翻页功能
		if ( itemsObj instanceof Collection ){
			NavRequest navRequest = HTMLTableHelper.getNavRequest(this.id, (HttpServletRequest)this.pageContext.getRequest(), pageSize);
			dataModel = HTMLTableHelper.getDataModel(navRequest, (Collection)itemsObj);
		}else{
	   	    dataModel = DataModelFactory.getInstance(itemsObj);
		}
		return EVAL_BODY_INCLUDE;
	}
	public int doEndTag() throws JspException {
		PageInfo pageInfo = this.getNavInfo();
		if ( pageInfo != null ){
			this.table.addParam(new HTMLParam(TableConstants.START_PARAM, String.valueOf(pageInfo.getStart()) ));
			this.table.addParam(new HTMLParam(TableConstants.PAGE_SIZE_PARAM, String.valueOf(pageInfo.getPageSize()) ));
		}
		
		SortInfo sortInfo = this.getSortInfo();
		if ( sortInfo != null && StringUtils.isNotEmpty(sortInfo.getSortProperty())  ){
				this.table.addParam(new HTMLParam(TableConstants.SORT_PROPERTY_PARAM,  sortInfo.getSortProperty()));
				this.table.addParam(new HTMLParam(TableConstants.SORT_DIR_PARAM, sortInfo.getSortDir() ));
				HTMLColumn column = (HTMLColumn)this.table.getColumn(sortInfo.getSortProperty());
    	        this.table.addParam(new HTMLParam(TableConstants.SORT_NAME_PARAM, column.getSortName() ));
		} else {
			this.table.addParam(new HTMLParam(TableConstants.SORT_PROPERTY_PARAM, "" ));
			this.table.addParam(new HTMLParam(TableConstants.SORT_NAME_PARAM, "" ));
			this.table.addParam(new HTMLParam(TableConstants.SORT_DIR_PARAM, "" ));
		}
		
		
		 TableDirector director = getTableDirector();
		 /**
		  * @fixme: 根据builder值获取builder对象
		  */
		 AbstractHTMLTableBuilder htmlBuilder = 
			 HTMLBuilderFactory.getInstance(builder);
		 director.build(htmlBuilder, table);
		 String treeScript = htmlBuilder.getTableScript();
		 try {
			this.pageContext.getOut().write(treeScript);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
			throw new JspException("创建表格:" + this.id + "失败!", e);
		}finally{
			cleanUp();
		}
		if ( this.enabledStateManager ){
	  		this.pageContext.getSession().setAttribute(TableConstants.ENABLED_STATE_MANAGER_PREFIX + this.id, new Boolean(enabledStateManager));
			this.pageContext.getSession().setAttribute(TableConstants.STATE_PARAM_PREFIX+ this.id, this.stateParam);
			SessionStateManager stateManager = new SessionStateManager(this.id, new JspPageWebContext(this.pageContext));
			StateInfo stateInfo = new DefaultStateInfo(this.id, sortInfo, pageInfo);
			stateManager.saveStateInfo(stateInfo);
		} 
		return super.doEndTag();
	}
	  /**
     * clean up instance variables, but not the ones representing tag attributes.
     */
    private void cleanUp()
    {
    	this.currRow = null;
		this.rowIndex = 0;		
		this.createdTable = false;
		this.createdHeader = false;
		this.columnProperties.clear();
		this.loopTagStatus = null;
	}
	protected TableDirector getTableDirector(){
		DefaultTableContext tableContext = new DefaultTableContext(
				new JspPageWebContext(this.pageContext),
				I18nResourceProviderFactory.getInstance(this.i18n),
				MessageSourceFactory.getInstance()
				);
		return new DefaultTableDirector( tableContext );
	}

	
    private LoopTagStatus loopTagStatus = null;
	public void release() {
		this.id = null;
		this.var = null;
		this.varStatus = null;
		this.items = null;
		this.scope = null;
		this.builder = null;
		
		this.currRow = null;
		this.rowIndex = 0;		
		this.createdTable = false;
		this.createdHeader = false;
		this.columnProperties.clear();
		this.loopTagStatus = null;
		this.captionKey = null;
		this.noDataTip = null;
		this.noDataTipKey = null;		
		this.columnProperties = null;
		this.table = null;

		
		super.release();
	}

	public String getVarStatus() {
		return varStatus;
	}

	public void setVarStatus(String varStatus) {
		this.varStatus = varStatus;
	}

	public HTMLRow getCurrRow() {
		return currRow;
	}

	public boolean isCreatedTable() {
		return createdTable;
	}

    public void addColumnProperty(String pProperty){
    	if ( pProperty == null ){
    		throw new IllegalArgumentException("列名不能为空null");
    	}
    	if ( this.columnProperties.contains(pProperty) ){
    	   logger.debug("指定列：" + pProperty + "已经存在！");	
    	}
    	this.columnProperties.add(pProperty);
    }
    

	public boolean isCreatedHeader() {
		return createdHeader;
	}

	public HTMLTable getTable() {
		return table;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public boolean isEnabledStateManager() {
		return enabledStateManager;
	}

	public void setEnabledStateManager(boolean enabledStateManager) {
		this.enabledStateManager = enabledStateManager;				
	}



	public String getStateParam() {
		return stateParam;
	}

	public void setStateParam(String stateParam) {
		this.stateParam = stateParam;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCaptionKey() {
		return captionKey;
	}

	public void setCaptionKey(String captionKey) {
		this.captionKey = captionKey;
	}

	public String getNoDataTip() {
		return noDataTip;
	}

	public void setNoDataTip(String noDataTip) {
		this.noDataTip = noDataTip;
	}

	public String getNoDataTipKey() {
		return noDataTipKey;
	}

	public void setNoDataTipKey(String noDataTipKey) {
		this.noDataTipKey = noDataTipKey;
	}


	

	
}
