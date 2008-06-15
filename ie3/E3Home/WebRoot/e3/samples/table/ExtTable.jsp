
<%@ include file="/e3/commons/Common.jsp"%>
<%@ taglib prefix="e3t" uri="/e3/table/E3Table.tld" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</HEAD>
<BODY> 

<form>
<c:url var="uri" value="/servlet/tableServlet?_actionType=showExtTable" />
<e3t:table id="userTable"
           var="user"
           varStatus="userStatus"
           items="users"
           caption="用户管理"
           uri="${uri}"
           pageSize="5"
           skin="E3002"
          >
    <e3t:column property="userID"   title="用户ID" />          
    <e3t:column property="userName" title="用户名称" />            
    <e3t:column property="sex"      title="性别" />                
    <e3t:column property="birthday"      title="生日" />                    
    <e3t:column property="remark"   title="备注" />
</e3t:table>
</form>          

${paramsForm}
</BODY>
</HTML>
