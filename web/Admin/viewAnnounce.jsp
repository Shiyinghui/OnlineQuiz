<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@page import="com.system.service.*"%>
<%@page import="java.util.*"%>
<%@page import="com.system.entity.*"%>
<%@ page import="com.system.entity.Administrator" %>
<%@ page import="com.system.entity.Announce" %>
<%@ page import="com.system.service.AnnounceService" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<link href="../resources/bootstrap/css/bootstrap.css"
		  rel="stylesheet">
	<link href="../resources/font-awesome/css/font-awesome.min.css"
		  rel="stylesheet">

	<link href="../css/adminmenu.css" rel="stylesheet">
	<!-- js and footer-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="../js/jquery-1.12.4.min.js"><\/script>')</script>
	<script src="../resources/bootstrap/js/bootstrap.min.js"></script>

	<script>
        $(function(){
            $(".footer").load("footer.html");
        });
	</script>

		<style>
			.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
				padding: 8px 0;
				line-height: 1.42857143;
				vertical-align: middle;
				border-top: 1px solid #ddd;
			}
			.question_td{
			}
			
			 .white_content { 
            display: none; 
            position: absolute; 
            top: 100px; 
            left: 15%; 
            width: 700px; 
            height: 200px; 
            padding: 5px; 
            border: 3px solid #5E42BD; 
            background-color: white; 
            z-index:5000; 
            overflow: auto;
		}
		</style>

<title>查看公告</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	Administrator admin = (Administrator) session.getAttribute("admin");
	String type = (String)session.getAttribute("type");
	boolean flag = (boolean) session.getAttribute("state");
	if (flag && type.equals("admin")) {
	    session.setAttribute("admin",admin);
	    session.setAttribute("state",true);
	    session.setAttribute("type","admin");
%>
<style>
	.maincontent {
		padding: 60px 15px;

	}
</style>

		<!-- Navigation bar starts -->

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
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
					<a href="AdminHome.jsp"><i class="fa fa-home fa-fw"></i>主页</a>
				</li>
				<li>
					<a href="getAllQLibrary.jsp"><i class="fa fa-edit fa-fw"></i>题库管理</a>
				</li>
				<li >
					<a href="HistoryQuiz.jsp"><i class="fa fa-dashboard fa-fw"></i>答题记录</a>
				</li>
				<li>
					<a href="manageQuestion.jsp"><i class="fa fa-cogs fa-fw"></i>试题管理</a>
				</li>
				<li >
					<a href="viewGamer.jsp"><i class="fa fa-cogs fa-fw"></i>玩家管理</a>
				</li>

				<li class="active">
					<a href="viewAnnounce.jsp"><i class="fa fa-cogs fa-fw"></i>公告管理</a>
				</li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<p class="navbar-text"><%="欢迎您，"+admin.getName()+"!" %></p>
				<a class="btn btn-danger navbar-btn" href="../index.jsp">退出</a>
			</ul>
		</div><!--/.nav-collapse -->
		<%
			type=null;
			admin=null;
		%>
	</div>
</nav>

		<!-- Navigation bar ends -->

			<!-- Slider (Flex Slider) -->

			<div class="container" style="min-height:500px;">

				<div class="row">
					<div class="col-xs-2" id="left-menu" style="margin-top: 40px">
						<ul class="nav default-sidenav">
							<li  class="active">
								<a href="viewAnnounce.jsp" title="查看历史公告"><i class="fa fa-volume-up">&nbsp</i><span class="left-menu-item-name"> 查看历史公告</span></a>
							</li>
							<li>
								<a href="addAnnounce.jsp" title="发布新公告"><i class="fa fa-plus-square">&nbsp</i><span class="left-menu-item-name"> 发布新公告</span></a>
							</li>
						</ul>
					</div>
					<div class="col-xs-10">
						<div style="border-bottom: 1px solid #ddd;">
							<h3 class="title"><i class="fa fa-dashboard"></i> 公告管理</h3>
						</div>	
						<div>
							<table class="table-striped table">
								
								<tr align="center">
											<td width="20%">公告名称</td>
											<td width="20%">具体内容</td>
											<td width="20%">发布时间</td>
								</tr>
<% 
							List<Announce> list=new AnnounceService().getAllAnnounce();
							int i=0;
							Announce notice=new Announce();
							if(list!=null){%>
							<% 
							Iterator<Announce> iterList=list.iterator();
							while(iterList.hasNext()){%>
								<%notice=iterList.next();
								i++;%>
							    <tr align="center">
											<td width="20%"><%=notice.getTitle() %></td>
											<td width="20%"  class="question_td"><%=notice.getContent()%></td>
											<td width="20%"><%=notice.getTime() %></td>

								</tr>
								 <div class="white_content">
								 <!-- <%=notice.getContent() %>
								 <br> -->
								 <!-- <button class="question_btn">关闭</button> -->
								</div>

								
						<% }
							%>
							</table>
								<% }
                             else{
							    out.println("目前系统内暂无公告！");
							}%>
						</div>

					</div>
				</div>
			</div>

	<div class="footer">
	</div>

<script src="../js/question.js"></script>
<%
	}
	else {
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

%>

</body>
</html>