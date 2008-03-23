<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/e3/commons/Common.jsp"%>
<%@ taglib uri="/WEB-INF/home/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/home/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/home/Home.tld" prefix="st"%>
<html:html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <link href="<c:url value='/home/css/admin.css'/>" rel="stylesheet">
   
   
   <script language="javascript" src="<c:url value='/home/js/JQuery.js'/>"></script>
   <script language="javascript" src="<c:url value='/home/js/JQuery.validate.js'/>"></script>
   <script language="javascript" src="<c:url value='/home/js/cmxforms.js'/>"></script>
   
   
  
 <script>
 
$(document).ready(function() {

  //确定
  $("#okBtn").click(function() {
     $("#dictForm").submit();
  });
  //取消
  $("#cancelBtn").click(function() {
     $("#cancelForm").submit();
  });
   
 //有效性校验.  
  $("#dictForm").validate({  
     event: "keyup",
     rules: {
 		  dictCode: { required : true, maxLength :32},
 		  dictName: { required : true, maxLength :32}
  	 },
     messages: {
 		dictCode: { required : "字典代码不能为空", maxLength : "字典代码最大长度不能操作32"},
 		dictName: { required : "字典名称不能为空", maxLength : "字典名称最大长度不能操作32"}
  	 }
  });
     
 }); 
$(window).load(function(){
   <c:if test="${not empty _tipMsg}">
     alert("${_tipMsg}");
   </c:if>
});  
 
 
</script>

  
</head>
<body>
   
  <%-- 取消时，返回列表页面 --%>                                 
  <html:form action="/dictAction"  styleId="cancelForm" method="POST"  >
    <input type="hidden" name="method" value="listDicts"/>
    <input type="hidden" name="_e3State" value="true"/>
  </html:form>

<table class="subjectTable" width="100%">
  <tr>
    <td align="center"><div class="subject">
        新增字典
    </div></td>
  </tr>
</table>
                                                                    
  <html:form action="/dictAction"  styleId="dictForm" method="POST" styleClass="cmxform"  enctype="multipart/form-data" >
    <input type="hidden" name="method" value="insertDict"/>
   <fieldset>
		<legend></legend>
		<p>
			<label for="dictCode">字典代码</label>
			<html:text property="dictCode" styleClass="field" />
		</p>
		<p>
			<label for="dictName">字典名称</label>
			<html:text property="dictName" styleClass="field" />
			<st:dict dictCode="vv" property="xxx"/>
		</p>
		<p>
			<label for="uploadFile">附件</label>
			<html:file property="uploadFile" styleClass="field" />
		</p>
		
		<p>
		<label for="remark">备注</label>
		    <html:textarea rows="3" cols="70" property="remark" styleId="remark" styleClass="area" />
		</p>

		<p>
		  <label for="remark"></label>
          <input type="button" class="button" value="<bean:message key="common.ok"/>"  id="okBtn">
	      <input type="button" class="button" value="<bean:message key="common.cancel"/>" id="cancelBtn">
		</p>
	</fieldset>
</html:form>

</body>
</html:html>


