<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.system.service.*"%>
<%@page import="java.util.*"%>
<%@page import="com.system.entity.*"%>
<%@ page import="com.system.entity.Gamer" %>
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
<html>
<head>
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

<title>主页</title>
</head>
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

					<li class="active">
						<a href="GamerHome.jsp"><i class="fa fa-home fa-fw"></i>主页</a>
					</li>
					<li>
						<a href="AnnounceBoard.jsp"><i class="fa fa-cogs fa-fw"></i>公告板</a>
					</li>
					<li>
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

	<!-- Slider starts -->
	<div class="full-slider">
		<!-- Slider (Flex Slider) -->

		<div class="container">
			<div class="jumbotron">
				<h1>Hello, world!</h1>
				<p>It works!</p>
				<p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
			</div>
			<div class="col-xs-10">
				<div style="border-bottom: 1px solid #ddd;">
					<h3 class="title"><i class="fa fa-cloud-upload"></i> 介绍</h3>
				</div>

				<!-- 	<div class="myDiv1" > -->
				<div class="full-slider">
					<!-- Slider (Flex Slider) -->
					<div class="container">

						<div class="row">
							<div class="col-md-12">
								<div class="flexslider">
									<div class="flex-caption">
										<!-- Left column -->
										<div class="col-l" style="width: 75%">
											<p style="text-indent:2em;">

                                                您知道咖啡的故乡在哪里嘛？您知道名著《简爱》的作者和《呼啸山庄》的作者是什么关系嘛？您知道亚洲第一个举办夏季奥运会的城市是哪个城市嘛？
                                                您知道世界上最大的内陆国家是哪个嘛？您知道美国的好莱坞电影公司坐落在哪儿嘛？呀，是不是突然发现真的有不了解的 o.o?
                                                这些都是一些生活、文学、地理、体育娱乐常识哟。在您工作非常疲惫的时候，在您玩游戏玩到厌倦的时候，来这里放松一下吧~。
                                                在娱乐的同时获取知识，是我们这个答题平台的宗旨。我们致力于开阔您的眼界，让您拥有更丰富有趣的知识！
											</p>

										</div>
										<!-- Right column -->
										<div class="col-r">

											<!-- Use the class "flex-back" to add background inside flex slider -->

											<!-- <img alt="" src="../resources/images/ad.png"> -->
											<p style=text-indent:2em;">
												如果您对软件有任何反馈和建议，加入我们的QQ群1234567890一起讨论吧!
											</p>

											<!-- Button -->
											<a class="btn btn-default btn-cta" href="../index.jsp"><i
													class="fa fa-arrow-circle-down"></i> 马上加入我们吧</a>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>

<%
	}
	else {
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
%>
    <div class="footer">
    </div>
</body>
</html>