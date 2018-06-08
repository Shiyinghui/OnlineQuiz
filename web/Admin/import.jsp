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
            $(function () {
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
        <title>增加题目</title>
    </head>
    <body>
        <% request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String type = (String) session.getAttribute("type");
            QLibrary ql = (QLibrary) session.getAttribute("currentQL");
            Administrator admin = (Administrator) session.getAttribute("admin");
            boolean flag = (boolean) session.getAttribute("state");
            if (flag && type.equals("admin")) {
                session.setAttribute("admin", admin);
                session.setAttribute("state", true);
                session.setAttribute("type", "admin");
                session.setAttribute("currentQL", ql);

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

                        <li>
                            <a href="AdminHome.jsp"><i class="fa fa-home fa-fw"></i>主页</a>
                        </li>
                        <li class="active">
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
            </div>
        </nav>
        <!-- Navigation bar ends -->
        <div>
            <!-- Slider (Flex Slider) -->

            <div class="container" style="min-height:500px;">

                <div class="row">
                    <div class="col-xs-2" id="left-menu">


                        <ul class="nav default-sidenav">
                            <li>
                                <a href="addQuestions.jsp" title="输入添加"><i class="fa fa-plus-square">&nbsp</i><span
                                        class="left-menu-item-name"> 输入添加</span></a>
                            </li>
                            <li class="active">
                                <a href="import.jsp" title="Excel导入"><i class="fa fa-volume-up">&nbsp</i><span
                                        class="left-menu-item-name">Excel导入</span></a>
                            </li>

                        </ul>
                    </div>

                    <div class="col-xs-10">
                        <div style="border-bottom: 1px solid #ddd;">
                            <h3 class="title"><i class="fa fa-dashboard"></i> Excel中导入</h3>
                        </div>
                        <div>


                            <br/>
                            <br/>
                            <form name="uploadFileForm" method="post" enctype="multipart/form-data"
                                  action="<%=request.getContextPath()%>/upLoadFile">
                                <input type="file" class="btn btn-primary" name="upFile" size=50/><br/> <br/>
                                <button class="btn btn-success">upload</button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="footer"></div>

        <%
            } else {
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
        %>

    </body>
</html>