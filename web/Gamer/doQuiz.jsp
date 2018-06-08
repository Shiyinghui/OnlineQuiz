<%@page import="com.system.service.QLibraryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.system.entity.Gamer"%>
<%@page import="java.util.*"%>
<%@ page import="com.system.entity.QLibrary" %>
<html>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<link href="../resources/bootstrap/css/bootstrap.css"
		  rel="stylesheet">
	<link href="../resources/font-awesome/css/font-awesome.min.css"
		  rel="stylesheet">
	<link href="../css/adminmenu.css" rel="stylesheet">

	<%--<link href="../css/mystyle.css" rel="stylesheet">--%>

	<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
	<script>window.jQuery || document.write('<script src="../js/jquery-1.12.4.min.js"><\/script>')</script>
	<script src="../resources/bootstrap/js/bootstrap.min.js"></script>
	<script>
        $(function () {
            $(".footer").load("footer.html");
        });
	</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线答题</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String type = (String) session.getAttribute("type");
	Gamer g = (Gamer) session.getAttribute("gamer");
	boolean flag = (boolean) session.getAttribute("state");
	if (flag && type.equals("gamer")) {
		session.setAttribute("gamer",g);
		session.setAttribute("type","gamer");
		session.setAttribute("state",true);

%>

<body>
	<!-- Navigation bar starts -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
						aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">OnlineQuiz</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<%--<li class="active"><a href="#">Home</a></li>--%>
					<%--<li><a href="#about">About</a></li>--%>
					<%--<li><a href="#contact">Contact</a></li>--%>

					<li>
						<a href="GamerHome.jsp"><i class="fa fa-home fa-fw"></i>主页</a>
					</li>
					<li>
						<a href="AnnounceBoard.jsp"><i class="fa fa-cogs fa-fw"></i>公告板</a>
					</li>
					<li class="active">
						<a href="doQuiz.jsp"><i class="fa fa-dashboard fa-fw"></i>在线答题</a>
					</li>
					<li>
						<a href="MyQuiz.jsp"><i class="fa fa-dashboard fa-fw"></i>我的答题</a>
					</li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<p class="navbar-text"><%="欢迎您，" + g.getName() + "!" %>
					</p>
					<a class="btn btn-danger navbar-btn" href="../index.jsp">退出</a>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</nav>
	<!-- Navigation bar ends -->
	<div class="container">
        <div style="border-bottom: 1px solid #ddd;">
            <h3 class="title"><i class="fa fa-book"></i> 题目列表</h3>
            <!-- 这里面的 是默认的第一题库 -->
        </div>
		<table class="table-striped table">
			<thead>

				<tr>
					<td>测试列表</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>测试类型</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
			<%
					List<QLibrary> QLlist = new QLibraryService().getAll();
					if (QLlist != null) {
						for (QLibrary ql : QLlist) {
			%>

				 <tr>
					<td><%=ql.getName()%></td>
					<!-- QUIZ的开始时间-->
					<td><%=ql.getStartTime()%></td>
					<!-- QUIZ的结束时间-->
					<td><span class="span-info question-number"><%=ql.getEndTime()%></span></td>
					<!-- QUIZ的类型-->
					<td><span class="span-success question-number-2"><%=ql.getType()%></span></td>
					<!-- 开始测试的按钮,以post方法传递参数 -->
					<td>
					<form action="Exam.jsp" method="post">
					<input type="hidden" name="qlID" value=<%=ql.getId() %>>
					<input type="hidden" name="qlName" value=<%=ql.getName() %>>
					<input type="hidden" name="qlType" value=<%=ql.getType() %>>
					<input type="hidden" name="startTime" value=<%=ql.getStartTime() %>>
					<input type="hidden" name="endTime" value=<%=ql.getEndTime() %>>
					<input type="hidden" name="amount" value=<%=ql.getAmount() %>>
					<button  class="btn btn-success btn-sm join-practice-btn" type="submit">开始测试</button>
					</form>
					</td>
				</tr>

			<%
				}
			}
			%>
			</tbody>
			<tfoot></tfoot>
		</table>

		<%
			}
			else {
				session.invalidate();
				response.sendRedirect("index.jsp");
			}
		%>
	</div>
	<div class="footer"></div>
</body>
</html>