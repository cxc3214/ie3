<%@ include file="/e3/commons/Common.jsp"%>
<%@ taglib prefix="e3t" uri="/e3/table/E3Table.tld" %>
<%@ page contentType="text/html; charset=utf-8"%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</HEAD>
<BODY>

这里输入值后，再翻页看看，是不是不会被刷新：<input type="text"/> 
<c:url var="uri" value="/servlet/tableServlet?_actionType=showAjaxTable"/>

<e3t:table id="userTable"
           var="user"
           varStatus="userStatus"
           paramsFormVar="ss"
           items="users"
           caption="用户管理"
           uri="${uri}"
           skin="E3001_004"
            toolbarPosition = "both"
           toolbarShowPolicy="need"
           mode="ajax"
          > 
    <e3t:column property="userID" style="width:120px"  title="用户ID" ></e3t:column>          
    <e3t:column property="userName" style="width:120px" title="用户名称" />            
    <e3t:column property="sex"      style="width:120px" title="性别" >                
             <c:choose>
               <c:when test="${user.sex == 1}">
                   男
               </c:when>
               <c:when test="${user.sex == 0}">
                   女
               </c:when>
               <c:otherwise>
                   没填
               </c:otherwise>
             </c:choose>  
    </e3t:column>
    <e3t:column property="birthday"   style="width:120px"   title="生日" >
       <fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日"/>
    </e3t:column>
    <e3t:column property="remark"   title="备注" style="text-align:left;" />
</e3t:table>
          ${ss}
</BODY>
</HTML>
