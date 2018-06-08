<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@ page import="com.system.entity.Announce" %>
<%@ page import="com.system.service.AnnounceService" %>
<%@ page import="com.system.entity.Gamer" %>
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
        <title>公告板</title>

        <style>
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td,
            .table > tbody > tr > td, .table > tfoot > tr > td {
                padding: 12px 12px;
                line-height: 1.42857143;
                vertical-align: middle;
                border-top: 1px solid #ddd;
            }
        </style>

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

                        <li>
                            <a href="GamerHome.jsp"><i class="fa fa-home fa-fw"></i>主页</a>
                        </li>
                        <li class="active">
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

        <div class="container">
            <div class="panel panel-primary" style="margin-top: 50px">
                <!-- Default panel contents -->
                <div class="panel-heading">公告板</div>
                <table class="table-striped table">
                    <thead>

                        <tr>
                            <td>公告标题</td>
                            <td>公告内容</td>
                            <td>上传时间</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            AnnounceService anns = new AnnounceService();
                            List<Announce> announceList = new ArrayList<Announce>();
                            announceList = anns.getAllAnnounce();
                            Iterator<Announce> iter = announceList.iterator();
                            while (iter.hasNext()) {
                                Announce temp = new Announce();
                                temp = iter.next();

                                out.println("<tr>");
                                out.println("<td>" + temp.getTitle() + "</td>"); //公告的标题
                                out.println("<td>" + temp.getContent() + "</td>");//公告的内容
                                out.println("<td><span class=\"span-info question-number\">" + temp.getTime() + "</span></td>");
                                out.println("</tr>");
                            } %>
                    </tbody>

                    <tfoot></tfoot>
                </table>
            </div>
        </div>
        <!-- 此处为公告板 -->
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