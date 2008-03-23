<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e3" uri="/e3/tree/E3Tree.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<script>

function showSelectedNode(){
 var selectModel= tree.getSelectionModel();
 var selectNode = selectModel.getSelectedNode();
 alert(selectNode.text + selectNode.id );   
}

</script>
</HEAD>
<BODY> 
<%
  java.util.List datas  = new java.util.ArrayList();
  java.util.Map data = new java.util.HashMap();
  data.put("id","10");
  data.put("parentId", null );
  data.put("name","总部");
  data.put("viewOrder", new Integer(0) );
  datas.add( data );
  
  java.util.Map data1 = new java.util.HashMap();
  data1.put("id","1010");
  data1.put("parentId", "10" );
  data1.put("name","子公司1");
  data1.put("viewOrder", new Integer(1) );
  datas.add( data1 );
  
      java.util.Map data2 = new java.util.HashMap();
  data2.put("id","1020");
  data2.put("parentId", "10" );
  data2.put("name","子公司2");
  data2.put("viewOrder", new Integer(2) );
  datas.add( data2 );  
    
  pageContext.setAttribute("orgs", datas);
  
%>
<table>
<tr>
<td>
<c:url var="orgIcon" value="/e3/samples/tree/Org.gif"/>
<c:url var="userIcon" value="/e3/samples/tree/User.gif"/>
<c:url var="subTree" value="/servlet/xtreeServlet?_actionType=loadExtSubOrgs&parentID=001"/>

<e3:tree var="org" items="orgs" builder="ExtLoadTree" >
  <e3:node id="${org.id}" parentId="${org.parentId}" name="${org.name}"
           icon="${orgIcon}"
           openIcon="${userIcon}"   
           subTreeURL="${subTree}"
           cls="dynamic"
  />
</e3:tree>

</td>
<td>


<e3:tree var="org" items="orgs" sortProperty="id" reverse="true">
  <c:set var="selected" value="false"/>
  <c:if test="${org.id=='1020'}">
    <c:set var="selected" value="true"/>
  </c:if>

  <e3:node id="B${org.id}" parentId="B${org.parentId}" name="${org.name}"
           icon="${orgIcon}" 
           openIcon="${userIcon}"
           action="javascript:alert('test')"
           nodeProperty="checkbox"
           selected="${selected}"
  />
</e3:tree>
</td>
</tr>
</BODY>
</HTML>


