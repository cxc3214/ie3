<%@ include file="/e3/commons/Common.jsp"%>
<%@ taglib prefix="e3t" uri="/e3/table/E3Table.tld" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</HEAD>
<BODY> 

<%
  net.jcreate.e3.table.test.TestList tests = new net.jcreate.e3.table.test.TestList(10, false);
  request.setAttribute("tests", tests);
%>
<e3t:table id="testTable"
           items="tests"
           caption="测试"
           var="test"
           varStatus="testStatus"
          >         
      <e3t:column property="id"    title="用户ID"  />          
      <e3t:column property="name"  title="用户名称" />
                
      <e3t:column property="email"      title="电子邮件" >
        <e3t:decorator cls="url" />
      </e3t:column>                
      <e3t:column property="status"      title="状态" />                    
      <e3t:column property="description"   title="描述" />
      
      <e3t:row>
         <e3t:attribute name="onmouseover" value="this.style.backgroundColor = '#CCCCFF'"/> 
         <e3t:attribute name="onmouseout" value="this.style.backgroundColor = ''"/>
         <e3t:attribute name="onclick" value="alert('用户名称:${test.name}')" />
      </e3t:row>

      
</e3t:table>          

</BODY>
</HTML>
