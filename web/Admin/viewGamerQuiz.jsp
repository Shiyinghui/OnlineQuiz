<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="com.system.service.*" %>
<%@page import="java.util.*" %>
<%@page import="com.system.entity.*" %>
<%@ page import="com.system.entity.Administrator" %>
<%@ page import="com.system.entity.Question" %>
<%@ page import="com.system.service.AnswerService" %>
<%@ page import="com.system.entity.Test" %>
<%@ page import="com.system.entity.Answer" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <title>Starter Template for Bootstrap</title>
    <!-- Bootstrap core CSS -->

    <link href="../resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../resources/font-awesome/css/font-awesome.min.css"
          rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../js/jquery-1.12.4.min.js"><\/script>')</script>
    <script src="../resources/bootstrap/js/bootstrap.min.js"></script>

    <script>
        $(function(){
            $(".footer").load("footer.html");
        });
    </script>
</head>
<body>
<%
    String type = (String) session.getAttribute("type");
    Administrator admin = (Administrator) session.getAttribute("admin");
%>
<style>
    body {
        padding-top: 50px;
    }
    .question-content {
        padding: 40px 15px;

    }

</style>
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
            <p class="navbar-text"><%="欢迎您，"+admin.getName()+"!" %></p>
            <a class="btn btn-danger" href="../index.jsp">退出</a>
            </ul>
        </div><!--/.nav-collapse -->
        <%
            type=null;
          admin=null;
        %>
    </div>

</nav>

<div class="container">
    <div class="question-content">

<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    type = (String) session.getAttribute("type");
    admin = (Administrator) session.getAttribute("admin");
    boolean flag = (boolean) session.getAttribute("state");
    if (flag && type.equals("admin")) {
        session.setAttribute("admin", admin);
        session.setAttribute("state", true);
        session.setAttribute("type", "admin");
        String testID = request.getParameter("testID");
        Test test = new Test();
        test.setTestID(Integer.parseInt(testID));
        Map<Question, Answer> map = new AnswerService().getAnswer(test);
        Question tempQ = new Question();
        Answer tempA = new Answer();
        int i = 1;
        String myAnswer = "";
        String correctAnswer = "";
        if (map != null) {
            for (Map.Entry<Question, Answer> me : map.entrySet()) {
                tempQ = me.getKey();
                tempA = me.getValue();
                if (tempA.getAnswerContent() == 1) {
                    myAnswer = "A";
                } else if (tempA.getAnswerContent() == 2) {
                    myAnswer = "B";
                } else if (tempA.getAnswerContent() == 3) {
                    myAnswer = "C";
                } else if (tempA.getAnswerContent() == 4) {
                    myAnswer = "D";
                } else if (tempA.getAnswerContent() == 0) {
                    myAnswer = "未作答";
                }
                if (tempQ.getCorrectAnswer() == 1) {
                    correctAnswer = "A";
                } else if (tempQ.getCorrectAnswer() == 2) {
                    correctAnswer = "B";
                } else if (tempQ.getCorrectAnswer() == 3) {
                    correctAnswer = "C";
                } else if (tempQ.getCorrectAnswer() == 4) {
                    correctAnswer = "D";
                } else {
                    correctAnswer = "无";
                }

%>
<div>
    <br/>
    第<%=i%>题
    <%=tempQ.getTitle() + "(" + tempQ.getScore() + "分)"%><br/><br/>
    A.<%=tempQ.getChoiceA()%><br>
    B.<%=tempQ.getChoiceB()%><br>
    C.<%=tempQ.getChoiceC()%><br>
    D.<%=tempQ.getChoiceD()%><br>
    答题者所选答案：<%=myAnswer %><br>
    正确答案：<%=correctAnswer %><br>

</div>
<%
        i++;
    }%>
<br/>
<% } else {
    out.println("系统繁忙，请稍候重试！");%>
<a href="viewGamerQuiz.jsp">返回</a>

<%
        }

    } else {
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
%>
    </div>
</div><!-- /.container -->
    <div class="footer"></div>
</body>
</html>