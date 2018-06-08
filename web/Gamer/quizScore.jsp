<%--
  Created by IntelliJ IDEA.
  User: 24757
  Date: 2018/5/26
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.system.entity.Gamer" import="com.system.service.*" %>
<%@ page import="com.system.entity.*" %>
<%@ page import="java.util.*" %>
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
    <title>测试报告</title>
</head>
<body>


      <%
          request.setCharacterEncoding("UTF-8");
          response.setCharacterEncoding("UTF-8");
          String type = (String) session.getAttribute("type");
          Gamer g = (Gamer) session.getAttribute("gamer");
          boolean flag = (boolean) session.getAttribute("state");
          int score = 0;   // 测试的分数，初始化为0
          Test test = new Test();
          long testID = -1;
          if (flag && type.equals("gamer")) {
              session.setAttribute("gamer",g);
              session.setAttribute("type","gamer");
              session.setAttribute("state",true);

              // 答题结束直接转的情况
              if(session.getAttribute("testID")!=null){
               testID = (long)session.getAttribute("testID");
               score = (int)session.getAttribute("score");

              test.setTestID(testID);}

              //查看答题历史时的情况
              if(request.getParameter("testID")!=null){
                  if(!request.getParameter("testID").equals("")){
                       testID = Long.parseLong(request.getParameter("testID"));
                       test.setTestID(testID);
                       test = new TestService().getTestRecord(test);
                       score = test.getTestScore();
                  }
              }

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

      <div>
          <h3>score: <%=score%>分！</h3>
      </div>
        <%      Map<Question,Answer> answerMap = new AnswerService().getAnswer(test);
              if(answerMap!=null){
                  int i=1;
                  String myAnswer = "";
                  String correctAnswer = "";
               for(Map.Entry<Question,Answer> me: answerMap.entrySet()){
                 Question tempQ = me.getKey();
                 Answer   tempA = me.getValue();
                   if(tempA.getAnswerContent()==1){
                       myAnswer="A";
                   }
                   else if(tempA.getAnswerContent()==2){
                       myAnswer="B";
                   }
                   else if(tempA.getAnswerContent()==3){
                       myAnswer="C";
                   }
                   else if(tempA.getAnswerContent()==4){
                       myAnswer="D";
                   }
                   else if(tempA.getAnswerContent()==0){
                       myAnswer="未作答";
                   }
                   if(tempQ.getCorrectAnswer()==1){
                       correctAnswer="A";
                   }
                   else if(tempQ.getCorrectAnswer()==2){
                       correctAnswer="B";
                   }
                   else if(tempQ.getCorrectAnswer()==3){
                       correctAnswer="C";
                   }
                   else if(tempQ.getCorrectAnswer()==4){
                       correctAnswer="D";
                   }
                   else {
                       correctAnswer="无";
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
          我的答案：<%=myAnswer %><br>
          正确答案：<%=correctAnswer %><br>

      </div>
      <%
              i++;
          }%>



<%    }
      else out.println("您没有答题，得分为 0 ！");
    }
    else {
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
%>
    </div>
    <div class="footer"></div>
</body>
</html>
