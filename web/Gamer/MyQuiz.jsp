<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.system.entity.Gamer" %>
<%@ page import="com.system.entity.Test" %>
<%@ page import="com.system.entity.QLibrary" %>
<%@page import="com.system.entity.*" %>
<%@page import="com.system.service.*" %>
<%@page import="java.util.*" %>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally" %>
<%@page import="jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode" %>

<%
    request.setCharacterEncoding("UTF-8");//设置编码方式
    response.setCharacterEncoding("UTF-8");
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

        <style>
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td,
            .table > tbody > tr > td, .table > tfoot > tr > td {
                padding: 8px 0;
                line-height: 1.42857143;
                vertical-align: middle;
                border-top: 1px solid #ddd;
            }
        </style>

    </head>
    <%

//        if (!(session.getAttribute("gamer") == null)) {
//            //先判断session以决定用户身份，如果身份不符合，不显示相应内容
//            Gamer g = (Gamer) session.getAttribute("gamer");
//            session.setAttribute("gamer", g);
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
                        <li>
                            <a href="doQuiz.jsp"><i class="fa fa-dashboard fa-fw"></i>在线答题</a>
                        </li>
                        <li class="active">
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

            <div class="row">
                <div class="col-xs-12">
                    <div style="border-bottom: 1px solid #ddd;">
                        <h3 class="title"><i class="fa fa-book"></i> 所有的历史</h3>

                    </div>

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

                        <%

                            Vector<Test> quizRecord = new TestService().getTestRecord(g);
                            Iterator<Test> iter = quizRecord.iterator();
                            while (iter.hasNext()) {
                                Test tempTest = iter.next();
                           // out.println(tempTest.getTestTime());

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
                                    long testID = tempTest.getTestID();
                                    int score = tempTest.getTestScore();
                                %>
                            </div>

                            <tr>
                                <td><%=tempQ.getName() %>
                                </td>

                                <!-- quiz的类型-->
                                <td><span class="span-info question-number"><%=tempQ.getType() %></span></td>
                                <!-- quiz的时间-->
                                <td><%=tempTest.getTestTime() %>
                                </td>
                                <!-- quiz的得分-->
                                <td><%=tempTest.getTestScore() %>
                                </td>
                                <form method="post" action="quizScore.jsp">
                                    <input type="hidden" name="testID" value=<%=tempTest.getTestID()%>>
                                    <td width="16%">
                                        <button class="btn btn-success"><%="查看"%>
                                        </button>
                                    </td>
                                </form>
                            </tr>
                            <%
                                        }
                                    }

                            %>
                        </tbody>
                        <tfoot></tfoot>
                    </table>
                    <!-- 这是结束include 的地方 -->
                </div>
            </div>
        </div>
        <div class="footer"></div>
        <%
            }
            else {
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
        %>
    </body>

</html>