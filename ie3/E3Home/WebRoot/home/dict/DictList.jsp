<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ include file="/e3/commons/Common.jsp"%>
<%@ taglib uri="/WEB-INF/home/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/home/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/home/Home.tld" prefix="home"%>
<%@ taglib prefix="e3t" uri="/e3/table/E3Table.tld" %>

<%@ page contentType="text/html; charset=utf-8"%>
<HTML>
<HEAD>
<link href="<c:url value='/home/css/Global.css'/>" rel="stylesheet">
<META http-equiv=Content-Type content="text/html; charset=utf-8">
   <script language="javascript" src="<c:url value='/home/js/JQuery.js'/>"></script>  
<script>
$(document).ready(function() {
//修改
$("a[@name=updateLink]").click(function(){
   $("#method").val("updateDictPage");   
   $("#oid").val( this.getAttribute("oid") );
   $("#dictForm").submit();
});

//删除
$("a[@name=deleteLink]").click(function(){   
   if(confirm("<bean:message key="common.delConfirm"/>") == false){
     return;
   }
   $("#method").val("deleteDict");  
   $("#oid").val( this.getAttribute("oid") );
   $("#dictForm").submit();
});
//新增
  $("#insertBtn").click(function() {
     $("#method").val("insertDictPage");
     $("#dictForm").submit();
  });
   
 });

$(window).load(function(){
   <c:if test="${not empty _tipMsg}">
     alert("${_tipMsg}");
   </c:if>
});  
  
  
</script>
</HEAD>

<BODY> 
<html:form action="/dictAction"  method="POST"  styleId="dictForm">
  <input type="hidden" id="method" name="method" />
  <input type="hidden" id="oid"    name="oid" />  
  <input type="hidden" id="_e3State" name="_e3State" value="true" />
</html:form> 

<table width="100%">
  <tr><td style="text-align:right">
    <input type="button" class="button" name="insertBtn" id="insertBtn" value="<bean:message key="common.insert"/>"/>
  </td></tr>
</table>
<c:url var="uri" value="/home/dict/dictAction.do?method=listDicts"/>
<e3t:table id="dictTable"
           var="dict"
           varStatus="dictStatus"
           items="dicts"
           caption="字典管理"
           uri="${uri}"
           i18n="struts"
          >
    <e3t:param name="_e3State" value="true"/>      
    <e3t:column property="dictCode"   title="字典代码" sortable="false"/>          
    <e3t:column property="dictName" title="字典名称" sortable="false"/>                           
    <e3t:column property="remark" title="备注" sortable="false"/>
    <e3t:column property="op"   title="操作" sortable="false" width="80px">
       <span class="URL">
           <a name="updateLink"  oid="${dict.oid}">修改</a>
           <a name="deleteLink" oid="${dict.oid}">删除</a>
        </span>            
    </e3t:column>                    
</e3t:table>          

</BODY>
</HTML>
