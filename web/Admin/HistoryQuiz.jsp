<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="com.system.service.*" %>
<%@page import="java.util.*" %>
<%@page import="com.system.entity.*" %>
<%@ page import="com.system.service.TestService" %>
<%@ page import="com.system.service.QLibraryService" %>
<%@ page import="com.system.entity.Administrator" %>
<%@ page import="com.system.entity.Gamer" %>
<%@ page import="com.system.entity.Test" %>
<%@ page import="com.system.entity.QLibrary" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <link href="../resources/bootstrap/css/bootstrap.css"
              rel="stylesheet">
        <link href="../resources/font-awesome/css/font-awesome.min.css"
              rel="stylesheet">
        <link href="../css/adminmenu.css" rel="stylesheet">
        <%--<link href="../css/style.css" rel="stylesheet">--%>
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
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td,
            .table > tbody > tr > td, .table > tfoot > tr > td {
                padding: 8px 0;
                line-height: 1.42857143;
                vertical-align: middle;
                border-top: 1px solid #ddd;
            }
        </style>
        <title>答题记录</title>
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
                        <li class="active">
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
        <div class="container">

            <div class="col-xs-10">
                <div style="border-bottom: 1px solid #ddd;">

                    <h3 class="title">
                        <i class="fa fa-trophy"></i> 答题记录
                    </h3>
                </div>
                <%="所有题库：" %>
                <ul class="nav nav-tabs">


                    <%
                        List<QLibrary> QLlist = new QLibraryService().getAll();
                        session.setAttribute("QLlist", QLlist);
                        for (int i = 0; i < QLlist.size(); i++) {
                    %>
                    <li role="presentation" id="<%=QLlist.get(i).getId()%>"><a
                            href="HistoryQuiz.jsp?QLibraryID=<%=QLlist.get(i).getId()%>"><%=QLlist.get(i).getName()%>
                    </a></li>
                    <%--<form method="post" action="HistoryQuiz.jsp">--%>
                    <%--<input type="hidden" name="QLibraryID" value=<%=QLlist.get(i).getId() %>>--%>
                    <%--<input type="submit" name="submit" value=<%=QLlist.get(i).getName() + "-" + QLlist.get(i).getType()%>>--%>
                    <%--</form><br> %>--%>


                    <%
                        }
                    %>
                </ul>
                <%
                    String qlID = request.getParameter("QLibraryID");
                    if (qlID == null || qlID.equals("")) {
                        if (QLlist != null && QLlist.size() > 0) {
                            qlID = String.valueOf(QLlist.get(0).getId());
                        }

                    }
                    if (qlID != null && !qlID.equals("")) {
                        for (int i = 0; i < QLlist.size(); i++) {
                            if (QLlist.get(i).getId() == Long.parseLong(qlID)) {
                %><br>


                <script>
                    var s="<%=QLlist.get(i).getId()%>";
                    $('#'+s).addClass("active");
                </script>


                <table>
                    <tr>
                        <td>
                            <%="当前题库：" + QLlist.get(i).getName()%>
                        </td>

                    </tr>
                </table>
                <%
                        }
                    }


                %>

                <%
                    QLibrary currentQL = new QLibrary();
                    currentQL.setId(Long.parseLong(qlID));
                    Map<Test, Gamer> map = new TestService().getTestRecord(currentQL);
                    Test test = new Test();
                    Gamer gamer = new Gamer();
                    int i = 1;
                    int score = 0;
                    String str = "查看";
                    if (map != null) {
                %>

                <table class="table-striped table">
                    <tr align="center">
                        <h6>历史答题信息</h6>
                    </tr>
                    <tr align="center">
                        <td width="16%">名称</td>
                        <!-- <td width="16%">性别</td> -->
                        <td width="16%">邮箱</td>
                        <td width="20%">答题时间</td>
                        <td width="16%">分数</td>
                        <td width="16%">操作</td>
                    </tr>
                    <%
                        for (Map.Entry<Test, Gamer> me : map.entrySet()) {
                            test = me.getKey();
                            gamer = me.getValue(); %>
                    <tr align="center">
                        <td width="16%"><%=gamer.getName() %>
                        </td>
                        <td width="16%"><%=gamer.getEmail() %>
                        </td>
                        <td width="20%"><%=test.getTestTime() %>
                        </td>
                        <td width="16%"><%=test.getTestScore()%>
                        </td>
                        <form method="post" action="viewGamerQuiz.jsp">
                            <input type="hidden" name="testID" value=<%=test.getTestID()%>>
                            <td width="16%">
                                <button class="btn btn-success"><%=str%>
                                </button>
                            </td>
                        </form>
                    </tr>
                    <%
                        } %>
                </table>

                <% }
                }

                %>
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