<%@ include file="/e3/commons/Common.jsp"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<script>
function openURL(pURL){
 parent.rightFrame.location.href = pURL;
}
function goHome(pURL){
 top.location.href = pURL;
}

</script>
</HEAD>
<BODY> 
<%= request.getAttribute("treeScript") %>


</BODY>
</HTML>


