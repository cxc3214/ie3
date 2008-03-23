<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ include file="/e3/commons/Common.jsp"%>
<%@ taglib uri="/WEB-INF/store/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/store/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/store/Store.tld" prefix="st"%>
<%@ taglib prefix="e3t" uri="/e3/table/E3Table.tld" %>

<%@ page contentType="text/html; charset=utf-8"%>
<HTML>
<HEAD>
<link href="<c:url value='/store/css/Global.css'/>" rel="stylesheet">
<META http-equiv=Content-Type content="text/html; charset=utf-8">
   <script language="javascript" src="<c:url value='/store/js/JQuery.js'/>"></script>  
<script>
$(document).ready(function() {

 });


  
</script>
</HEAD>

<BODY> 
登陆对话框 
<!--登陆对话框 -->
<st:box id="loginBox">
  <st:template name="loginUI">
    <st:templateParameter name="userIDUI">
      <input type="text" class="field"   name="userID">
    </st:templateParameter>
    <st:templateParameter name="userPWDUI">
       <input type="text" class="field"  name="userPWD">
    </st:templateParameter>
  </st:template>
</st:box>



<!-- 主页布局　-->
<st:layout name="indexLayout">
</st:layout>  

</BODY>
</HTML>
