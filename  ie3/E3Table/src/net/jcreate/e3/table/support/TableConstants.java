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
package net.jcreate.e3.table.support;

/**
 * 常量值.
 * @author 黄云辉
 *
 */
public class TableConstants {
  private TableConstants(){
	  
  }
  
  /**
   * 存储pageSize信息的key值
   */
  public static final String PAGE_SIZE_KEY = "e3.table.pageSize";
  /**
   * 默认排序方式
   */
  public static final String SORTABLE_KEY = "e3.table.sortable";
  
  public static final String TOOLBAR_POSITION_KEY = "e3.table.toolbarPosition";
  public static final String TOOLBAR_SHOW_POLICY_KEY = "e3.table.toolbarShowPolicy";
  
  public static final String SKIN_KEY = "e3.table.skin";
  public static final String MODE_KEY = "e3.table.mode";
  /**
   * 命名空间/前缀
   */
  public static final String PREFIX = "net_jcreate_e3_table_html_";
  
  /**
   * 父亲资源
   */
  public static final String PARENT_MESSAGE_SOURCE_BASE = "net/jcreate/e3/table/message/E3Table";
  
  
  /**
   * 儿子资源
   *  业务系统可以定义E3Table.properties属性文件来覆盖e3table的默认设置.
   */
  public static final String CHILD_MESSAGE_SOURCE_BASE = "E3Table";
  
  /**
   * 状态信息key
   */
  public static final String STATE_INFO_PREFIX = PREFIX + "_" + "stateInfo_";
  
  /**
   * 状态参数
   */
  public static final String STATE_PARAM_PREFIX = PREFIX + "_" + "stateParam_";
  

  
  /**
   * 工具条显示位置
   */
  public static final String TOP_POSITION = "top";
  public static final String BOTTOM_POSITION = "bottom";
  public static final String BOTH_POSITION = "both";
  public static final String DEFAULT_TOOLBAR_POSITION = BOTH_POSITION;
  
  
  /**
   * 总是显示
   */
  public static final String ALWAYS_POLICY = "always";
  public static final String NEED_POLICY = "need";
  public static final String NONE_POLICY = "none";
  public static final String DEFAULT_TOOLBAR_SHOW_POLICY = ALWAYS_POLICY;
  
  
  
   
  
  
  
  /**
   * 起用状态管理key
   */
  public static final String ENABLED_STATE_MANAGER_PREFIX = PREFIX + "ENABLED_STATE_MANAGER_KEY_";
  
  
  /**
   * 默认每页显示记录数
   */
  public static final int  DEFAULT_PAGE_SIZE = 20;
  /**
   * 启用状态管理
   */
  public static final boolean  DEFAULT_ENABLED_STATE_MANAGER = true;
  
  /**
   * 默认状态参数，当request中存在该参数，并且起用了状态管理时，则可以恢复table状态
   */
  public static final String DEFAULT_STATE = "_e3State";
  
  /**
   * 表格模式
   */
  public static final String MODE_AJAX = "ajax";
  public static final String MODE_MVC = "mvc";
  public static final String DEFAULT_MODE = MODE_MVC;
  
  /**
   * 默认皮肤
   */
  public static final String DEFAULT_SKIN = "E3001";
  
  /**
   * 默认参数form保存范围
   */
  public static final String DEFAULT_PARAMSFORM_SCOPE = "request";
  /**
   * 默认builder
   */
  public static final String DEFAULT_BUILDER = "default";
  
  /**
   * 默认排序设置，（是否可以排序)
   */
  public static final boolean DEFAULT_SORTABLE = true;
  /**
   * 默认排序方向
   */
  public static final String DEFAULT_SORT_DIR = "none";
  
  /**
   * 升序
   */
  public static final String SORT_ASC = "asc";
  /**
   * 降序
   */
  public static final String SORT_DESC = "desc";
  /**
   * 无序
   */
  public static final String SORT_NONE = "none";
  
  
  /**
   * 用于记录 start参数值
   */
  public static final String START_PARAM = PREFIX + "start";
  
  /**
   * 用于记录 pageSize参数值
   */
  public static final String PAGE_SIZE_PARAM = PREFIX + "pageSize";
  
  /**
   * 导出参数
   */
  public static final String EXPORTED_PARAM = PREFIX + "exported";
  /**
   * 刷新区域
   */
  public static final String REFRESH_ZONE_PARAM = PREFIX + "refreshZone";
  
  public static final String EXPORT_FILE_NAME_PARAM = PREFIX + "fileName";
  
  
  /**
   * 导出类型参数
   */
  public static final String EXPORT_TABLE_PARAM = PREFIX +"exportTable";
  
  /**
   * 存放不导出的列,各列以逗号分割
   */
  public static final String EXPORT_EXCLUDES_PARAM = PREFIX + "excludes";
  public static final String PARAM_SPLITER = ", ;";
  
  /**
   * 导出bean前缀
   */
  public static final String EXPORT_BEAN_PREFIX = EXPORT_TABLE_PARAM + "_";
  
  /**
   * 报表内容key
   */
  public static final String REPORT_CONTENT_KEY = "reportContent";
  /**
   * 报表内容类型
   */
  public static final String REPORT_CONTENT_TYPE_KEY = "reportContentType";
  
   
  
  /**
   * 导出参数
   */
  public static final String EXPORT_PARAM_PARAM = PREFIX +"exportParam";
  
  /**
   * 皮肤参数
   */
  public static final String SKIN_PARAM = "_e3Skin";
  
  
  
  /**
   * 用于记录 sortColumn参数值
   */
  public static final String SORT_PROPERTY_PARAM = PREFIX + "sortProperty";
  
  /**
   * 排序名称，用于构造排序代码用的
   */
  public static final String SORT_NAME_PARAM = PREFIX + "sortName";
  
  
  
  /**
   * 用于记录 sortDir参数值
   */
  public static final String SORT_DIR_PARAM = PREFIX + "sortDir";
  
  /**
   * 初始脚本
   */
  public static final String BEGIN_SCRIPT_ID = "scriptBegin";
  /**
   * 结束脚本
   */
  public static final String END_SCRIPT_ID = "scriptEnd";
  
  /**
   * doc开始
   */
  public static final String DOC_BEGIN_ID = "docBegin";
  
  /**
   * doc结束 
   */
  public static final String DOC_END_ID = "docEnd";
  
  /**
   * 标题ID
   */
  public static final String CAPTION_ID = "caption";
  
  /**
   * 上导航条
   */
  public static final String TOP_NAVIGATION_ID = "topNavigation";
  
  /**
   * 下导航条
   */
  public static final String BOTTOM_NAVIGATION_ID = "bottomNavigation";
  
  
  /**
   * body开始
   */
  public static final String BODY_BEGIN_ID = "bodyBegin"; 
  /**
   * body结束
   */
  public static final String BODY_END_ID = "bodyEnd";
  
  /**
   * table开始
   */
  public static final String TABLE_BEGIN_ID = "tableBegin";
  
  /**
   * table 结束
   */
  public static final String TABLE_END_ID = "tableEnd";
  
  /**
   * header 开始
   */
  public static final String HEADER_BEGIN_ID = "headerBegin";
  
  public static final String COLUMN_GROUPS_BEGIN_ID = "columnGroupsBegin";
  public static final String COLUMN_GROUPS_END_ID = "columnGroupsEnd";
  public static final String COLUMN_GROUP_BEGIN_ID = "columnGroupBegin";
  public static final String COLUMN_GROUP_ID = "columnGroup";
  public static final String COLUMN_GROUP_END_ID = "columnGroupEnd";
  
  /**
   * header结束
   */
  public static final String HEADER_END_ID = "headerEnd";
  
  /**
   * column 开始
   */
  public static final String COLUMN_BEGIN_ID = "columnBegin";
  
  /**
   * column
   */
  public static final String COLUMN_ID = "column";
  
  /**
   * column结束
   */
  public static final String COLUMN_END_ID = "columnEnd";
  
  /**
   * row 开始
   */
  public static final String ROW_BEGIN_ID = "rowBegin";
  
  /**
   * 没数据的行
   */
  public static final String NO_DATA_ROW_ID = "noDataRow";
  
  /**
   * row 结束
   */
  public static final String ROW_END_ID = "rowEnd";
  
  /**
   * cell 开始
   */
  public static final String CELL_BEGIN_ID = "cellBegin";
  /**
   * cell
   */
  public static final String CELL_ID = "cell";
  /**
   * cell 结束
   */
  public static final String CELL_END_ID = "cellEnd";
  
  /**
   * 参数form
   */
  public static final String  PARAMS_FORM_ID = "paramsForm"; 
  
  /**
   * 没有数据时的提示信息key
   */
  public static final String NO_DATA_TIP_KEY = "e3.table.noDataTip";
  /**
   * 默认的i18 
   */
  public static final String DEFAULT_I18N = "struts";
  
  
  
  

  
}
