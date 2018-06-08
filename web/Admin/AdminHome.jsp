<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.system.entity.*" %>
<%@ page import="com.system.entity.Administrator" %>
<!DOCTYPE html>
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
        </style>


        <title>OnlineQuiz</title>
    </head>
    <body>

        <%
            String type = (String) session.getAttribute("type");
            Administrator admin = (Administrator) session.getAttribute("admin");
            boolean flag = (boolean) session.getAttribute("state");
            if (flag && type.equals("admin")) {
                session.setAttribute("admin", admin);
                session.setAttribute("state", true);
                session.setAttribute("type", "admin");

        %>
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
                            <a href="AdminHome.jsp"><i class="fa fa-home fa-fw"></i>主页</a>
                        </li>
                        <li>
                            <a href="getAllQLibrary.jsp"><i class="fa fa-edit fa-fw"></i>题库管理</a>
                        </li>
                        <li>
                            <a href="HistoryQuiz.jsp"><i class="fa fa-dashboard fa-fw"></i>答题记录</a>
                        </li>
                        <li>
                            <a href="manageQuestion.jsp"><i class="fa fa-cogs fa-fw"></i>试题管理</a>
                        </li>
                        <li>
                            <a href="viewGamer.jsp"><i class="fa fa-cogs fa-fw"></i>玩家管理</a>
                        </li>

                        <li>
                            <a href="viewAnnounce.jsp"><i class="fa fa-cogs fa-fw"></i>公告管理</a>
                        </li>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <p class="navbar-text"><%="欢迎您，" + admin.getName() + "!" %>
                        </p>
                        <a class="btn btn-danger navbar-btn" href="../index.jsp">退出</a>
                    </ul>
                </div><!--/.nav-collapse -->
                <%
                    type = null;
                    admin = null;
                %>
            </div>
        </nav>
        <!-- Navigation bar ends -->

        <!-- Slider starts -->

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

                                                “衣带渐宽终不悔，为伊消得人憔悴。”
                                                OnlineQuiz知识问答系统自发布后，获得了广大网友的欢迎及喜爱。这些成果的背后是管理员们辛苦的付出，搜集高质量的题目，让答题者们娱乐的同时开阔眼界，了解更多知识从而丰富生活，是我们不懈的追求。
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
                <!-- 	</div> -->
            </div>

        </div>


        <%
            } else {
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
        %>
        <div class="footer">
        </div>

    </body>
</html>