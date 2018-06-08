<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.system.entity.*"%>
<%@ page import="com.system.service.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.system.entity.Administrator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String type = (String) session.getAttribute("type");
	Administrator admin = (Administrator) session.getAttribute("admin");
	boolean flag = (boolean) session.getAttribute("state");
	if (flag && type.equals("admin")) {
		session.setAttribute("admin",admin);
		session.setAttribute("state",true);
		session.setAttribute("type","admin");
		if(request.getParameter("QLibraryID")!=null){
		long QLibraryID = Long.parseLong(request.getParameter("QLibraryID"));
		QLibrary ql = new QLibrary();
		ql.setId(QLibraryID);
		session.setAttribute("currentQL",ql);
		Vector<Question> quesList = new QuestionService().getAllQuestionOfQL(ql);
	    if (quesList != null) {
		Iterator<Question> iterList = quesList.iterator();
		int i = 1;
		while (iterList.hasNext()) {
			Question o = iterList.next();
%>
<br />
<br />
<br />
<br />
<br />
<%=i%>：<%=o.getTitle()%>
<%="(" + o.getScore() + "分)"%><br /> 选项A:&nbsp;<%=o.getChoiceA()%><br />
选项B:&nbsp;<%=o.getChoiceB()%><br /> 选项C:&nbsp;<%=o.getChoiceC()%><br /> 选项D:&nbsp;<%=o.getChoiceD()%><br />
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
<form method="post" action="modifyQuestion.jsp">
	<input type="hidden" name="questionID" value=<%=o.getId() %>>
	<input type="hidden" name="questionName" value=<%=o.getTitle() %>>
	<input type="hidden" name="answerA" value=<%=o.getChoiceA() %>>
	<input type="hidden" name="answerB" value=<%=o.getChoiceB() %>>
	<input type="hidden" name="answerC" value=<%=o.getChoiceC() %>>
	<input type="hidden" name="answerD"value=<%=o.getChoiceD() %>>
	<input type="hidden" name="correctAnswer" value=<%=o.getCorrectAnswer() %>>
	<input type="hidden" name="score" value=<%=o.getScore() %>>
	<input type="submit" name="submit" value="修改问题" >
</form>
<br>
<form method="post" action="deleteQuestion">
	<input type="hidden" name="questionID" value=<%=o.getId() %>>
	<input type="submit" name="submit" value="删除问题" >
</form>
<%
				i++;
			}
		}

	}
%>

   <%
	} else {
				session.invalidate();
				response.sendRedirect("index.jsp");
			}
   %>
		<a href="getAllQLibrary.jsp">返回</a>
</body>
</html>