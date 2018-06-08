<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.system.entity.*"%>
<%@page import="com.system.service.*"%>
<%@page import="java.util.*"%>
<%@ page import="com.system.service.TestService" %>

<%@ page import="com.system.entity.Gamer" import="com.system.entity.Administrator" %>
<%@ page import="com.system.entity.Test" %>
<%@ page import="com.system.entity.QLibrary" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link href="../resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link href="../resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<style>
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	padding: 8px 0;
	line-height: 1.42857143;
	vertical-align: middle;
	border-top: 1px solid #ddd;
}

a.join-practice-btn {
	margin-top: 0;
}
 .white_content1 { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 500px; 
            height: 300px; 
            padding: 5px; 
            border: 3px solid #5E42BD; 
            background-color: white; 
            z-index:1002; 
            overflow: auto;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String type = (String) session.getAttribute("type");
	Gamer g = (Gamer) session.getAttribute("gamer");
	boolean flag = (boolean) session.getAttribute("state");
	if (flag && type.equals("gamer")) {
		session.setAttribute("gamer", g);
		session.setAttribute("type", "gamer");
		session.setAttribute("state", true);
%>
<body>

	<table class="table-striped table">
		<!-- 这里大概是标题内容 -->
		<thead>

			<tr>
				<td>测试名字</td>
				<td>测试类型</td>
				<td>时间</td>
				<td>得分</td>
				<%--<td>查看</td>--%>
				<!-- <td>操作</td> -->
			</tr>
		</thead>
		<!-- 这里大概是标题内容的结束 -->
		<%

			Vector<Test> quizRecord = new TestService().getTestRecord(g);
			Iterator<Test> iter = quizRecord.iterator();
			while (iter.hasNext()) {
				Test tempTest = iter.next();

		%>
			<tbody>
		<%

					QLibrary tempQ = null;
					Map<QLibrary, Administrator> detailMap = new TestService().getDetailOfTest(tempTest);

					//获得详细信息
					for (Map.Entry<QLibrary, Administrator> entry : detailMap.entrySet()) {
						tempQ = entry.getKey();
					}
					if (tempTest != null && tempQ != null) {

		%>

			<div id="light" class="white_content1">
			 <%
			 long testID=tempTest.getTestID();
			 int score=tempTest.getTestScore();
			 %>
		</div>

			<tr>
				<td><%=tempQ.getName() %></td>

				<!-- quiz的类型-->
				<td><span class="span-info question-number"><%=tempQ.getType() %></span></td>
				<!-- quiz的时间-->
				<td><%=tempTest.getTestTime() %></td>
				<!-- quiz的得分-->
				<td><%=tempTest.getTestScore() %></td>
				<form method="post" action="quizScore.jsp">
					<input type="hidden" name="testID" value=<%=tempTest.getTestID()%>>
					<td width="16%"><button class="btn btn-success"><%="查看"%></button></td>
				</form>
			</tr>
	<%
				}
			}

		%>
		</tbody>
		<tfoot></tfoot>
	</table>
	<%
		} else {
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
	%>
</body>

</html>