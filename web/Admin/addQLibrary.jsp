<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.system.entity.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.system.entity.Administrator" %>
<%@ page import="com.system.entity.TypeBank" %>
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

        <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
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

            a.join-practice-btn {
                margin-top: 0;
            }
        </style>
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
        <div class="container">
            <form method="post" action="addLibrary">

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="bankName">请输入题库名：</label>
                        <input type="text" name="bankName" class="form-control" id="bankName">
                    </div>
                    <div class="form-group col-md-6">
                                                    <label for="bankType">请选择题库类型：</label>
                                    <%
                            List<String> banklists = new TypeBank().getBankType();
                            if (banklists != null) {
                        %>
                                <select class="form-control" name="bankType" id="bankType">
                                    <%
                                        for (int i = 0; i < banklists.size(); i++) {
                                    %>

                            <option value=<%=banklists.get(i) %>><%=banklists.get(i) %>
                            </option>

                            <%
                                }

                            %>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                    <label for="bankType">请设置答题数目：</label>
                    <input type="number" class="form-control" name="amount"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="startTime">开始时间： </label>
                        <input type="datetime-local" class="form-control" id="startTime" name="startTime"/>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="endTime">结束时间：</label>
                        <input type="datetime-local" class="form-control" id="endTime" name="endTime"/>
                    </div>
                </div>
            <input type="submit" class="form-control btn btn-success" name="submit" value="确定"><br>

            </form>

        </div>
        <%
                } else {
                    out.println("无法获取题库类型！");
                }
            } else {

                session.invalidate();
                response.sendRedirect("index.jsp");
            }

        %>
        <div class="footer"></div>
    </body>
</html>