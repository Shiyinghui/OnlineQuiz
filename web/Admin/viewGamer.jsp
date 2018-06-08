<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@ page import="com.system.entity.Administrator" %>
<%@ page import="com.system.entity.Gamer" %>
<%@ page import="com.system.service.ConsultService" %>
<!DOCTYPE html>
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
        <title>玩家管理</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
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

                        <li>
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
                        <li class="active">
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

            <div class="col-xs-10">
                <div style="border-bottom: 1px solid #ddd;">
                    <h3 class="title"><i class="fa fa-bar-chart-o"></i> 玩家管理</h3>
                </div>
                <div class="page-content">
                    <div id="teacher-list">
                        <br>选择要删除的玩家：<br>
                        <form method="post" action="deleteGamer">
                            <table class="table-striped table">
                                <tr align="center">

                                    <td width="25%">ID</td>
                                    <td width="25%">姓名</td>
                                    <td width="25%">邮箱</td>
                                    <td width="25%">打勾</td>
                                </tr>
                                <%
                                    Vector<Gamer> allGamer = new ConsultService().getAllGamers();
                                    if (allGamer != null) {
                                        Iterator<Gamer> iter = allGamer.iterator();

                                        while (iter.hasNext()) {
                                            Gamer g = iter.next();%>
                                <tr align="center">

                                    <td width="25%"><%=g.getId()%>
                                    </td>
                                    <td width="25%"><%=g.getName()%>
                                    </td>
                                    <td width="25%"><%=g.getEmail()%>
                                    </td>
                                    <td width="25%"><input type="checkbox" name="deleteGamer" value=<%=g.getEmail()%>>
                                    </td>
                                </tr>

                                <% }
                                }%>

                            </table>
                            <button class="btn btn-success">删除</button>
                        </form>
                    </div>
                    <div id="page-link-content">
                        <ul class="pagination pagination-sm">

                        </ul>
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