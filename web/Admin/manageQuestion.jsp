<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@ page import="com.system.service.QLibraryService" %>
<%@ page import="com.system.entity.Administrator" %>
<%@ page import="com.system.entity.Question" %>
<%@ page import="com.system.entity.QLibrary" %>
<%@ page import="com.system.service.QuestionService" %>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="../js/jquery-1.12.4.min.js"><\/script>')</script>
        <script src="../resources/bootstrap/js/bootstrap.min.js"></script>

        <script>
            $(function(){
                $(".footer").load("footer.html");
            });
        </script>
        <%--<link href="../css/style.css" rel="stylesheet">--%>
        <%--<link href="../css/mystyle.css" rel="stylesheet">--%>

        <style>
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
                padding: 8px 0;
                line-height: 1.42857143;
                vertical-align: middle;
                border-top: 1px solid #ddd;
            }

            .white_content {
                display: none;
                position: absolute;
                top: 100px;
                left: 15%;
                width: 700px;
                height: 200px;
                padding: 5px;
                border: 3px solid #5E42BD;
                background-color: white;
                z-index: 5000;
                overflow: auto;
            }

        </style>
        <title>试题管理</title>

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
                        <li class="active">
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


        <div class="container">

            <div class="col-xs-10">
                <div style="border-bottom: 1px solid #ddd;">
                    <h3 class="title"><i class="fa fa-cogs"></i> 试题管理</h3>
                </div>
                <%="所有题库：" %>
                <ul class="nav nav-tabs">


                    <%
                        List<QLibrary> QLlist = new QLibraryService().getQLibrary(admin);
                        session.setAttribute("QLlist", QLlist);
                        for (int i = 0; i < QLlist.size(); i++) {
                    %>
                    <li role="presentation" id="<%=QLlist.get(i).getId()%>"><a
                            href="manageQuestion.jsp?QLibraryID=<%=QLlist.get(i).getId()%>"><%=QLlist.get(i).getName()%>
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

                        // 系统内有题库，默认当前题库为第一个
                        if (QLlist != null && QLlist.size() > 0) {
                            qlID = String.valueOf(QLlist.get(0).getId());
                        }
                    }
                    if (qlID != null && !qlID.equals("")) {
                        for (int i = 0; i < QLlist.size(); i++) {
                            if (QLlist.get(i).getId() == Long.parseLong(qlID)) {
                %>
                <script>
                    var s="<%=QLlist.get(i).getId()%>";
                    $('#'+s).addClass("active");
                </script>

                <%="当前题库：" + QLlist.get(i).getName()%>
                <div>


                    <table class="table-striped table">
                        <tr align="center">
                            <td width="25%">题号</td>
                            <td width="25%">内容</td>
                            <td width="25%">分数</td>
                            <td width="25%">操作</td>
                        </tr>
                        <% }
                        }

                            QLibrary currentQL = new QLibrary();
                            currentQL.setId(Long.parseLong(qlID));
                            Vector<Question> quesList = new QuestionService().getAllQuestionOfQL(currentQL);
                            if (quesList != null) {
                                Iterator<Question> iterList = quesList.iterator();
                                int i = 0;
                                while (iterList.hasNext()) {
                                    Question o = iterList.next();
                                    i++;
                        %>
                        <tr align="center" height="100px">
                            <td width="25%"><%=i %>
                            </td>
                            <td width="25%" class="question_td"><a><%
                                String str = o.getTitle();
                                if (str.length() > 15) {
                                    char[] arr = str.toCharArray();
                                    char[] temp = new char[15];
                                    for (int j = 0; j < 15; j++) {
                                        temp[j] = arr[j];
                                    }
                                    str = String.valueOf(temp);
                                    out.println(str);
                                } else {
                                    out.println(str);
                                }
                            %></a></td>
                            <td width="25%"><%=o.getScore() %>
                            </td>
                            <td width="25%">
                                <form method="post" action="modifyQuestion.jsp">
                                    <input type="hidden" name="questionID" value=<%=o.getId() %>>
                                    <input type="hidden" name="questionName" value=<%=o.getTitle() %>>
                                    <input type="hidden" name="answerA" value=<%=o.getChoiceA() %>>
                                    <input type="hidden" name="answerB" value=<%=o.getChoiceB() %>>
                                    <input type="hidden" name="answerC" value=<%=o.getChoiceC() %>>
                                    <input type="hidden" name="answerD" value=<%=o.getChoiceD() %>>
                                    <input type="hidden" name="correctAnswer" value=<%=o.getCorrectAnswer() %>>
                                    <input type="hidden" name="score" value=<%=o.getScore() %>>
                                    <button class="change-property btn-sm btn-info"><i
                                            class="ace-icon fa fa-pencil bigger-120"></i></button>
                                </form>

                                <form method="post" action="deleteQuestion">
                                    <input type="hidden" name="questionID" value=<%=o.getId() %>>
                                    <button class="delete-question-btn btn-sm btn-danger"><i
                                            class="ace-icon fa fa-trash-o bigger-120"></i></button>
                                </form>
                            </td>
                        </tr>
                        <div class="white_content">
                            <%=i%>.<%=o.getTitle()

                        %>
                            <%="(" + o.getScore() + "分)"%><br/>选项A:<%=o.getChoiceA()%><br/>
                            选项B:<%=o.getChoiceB()%><br/> 选项C:<%=o.getChoiceC()%><br/> 选项D:<%=o.getChoiceD()%>
                            <br/>
                            正确答案：
                            <%
                                int correct = o.getCorrectAnswer();
                                switch (correct) {
                                    case 1:
                                        out.println("A");
                                        break;
                                    case 2:
                                        out.println("B");
                                        break;
                                    case 3:
                                        out.println("C");
                                        break;
                                    case 4:
                                        out.println("D");
                                        break;
                                }
                            %>
                            <br/>
                            <button class="question_btn">关闭</button>
                        </div>
                        <%

                                    }
                                }
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>
        <div class="footer">
        </div>
        <script src="../js/question.js"></script>
        <%
            } else {
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
        %>
    </body>
</html>