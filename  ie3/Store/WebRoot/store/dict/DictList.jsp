<%@ include file="/e3/commons/Common.jsp"%>
<%@ taglib uri="/WEB-INF/store/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/store/struts-html.tld" prefix="html"%>
<%@ taglib prefix="e3t" uri="/e3/table/E3Table.tld" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</HEAD>
<BODY> 


<c:url var="uri" value="/store/dict/dictAction.do?method=listDicts"/>
<e3t:table id="dictTable"
           var="dict"
           varStatus="dictStatus"
           items="dicts"
           caption="字典管理"
           uri="${uri}"
           i18n="struts"
          >
    <e3:param name="_e3State" value="true"/>      
    <e3t:column property="dictCode"   title="字典代码" titleKey="a"/>          
    <e3t:column property="dictName" title="字典名称" />            
    <e3t:column property="viewOrder"      title="序号" />                
    <e3t:column property="remark"      title="备注" />                    
</e3t:table>          

</BODY>
</HTML>
