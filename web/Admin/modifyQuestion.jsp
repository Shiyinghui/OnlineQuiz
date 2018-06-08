<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.system.service.*"%>
<%@page import="java.util.*"%>
<%@page import="com.system.entity.*"%>
<%@ page import="com.system.entity.Administrator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改试题</title>
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
		String questionName=request.getParameter("questionName");
		String answerA=request.getParameter("answerA");
		String answerB=request.getParameter("answerB");
		String answerC=request.getParameter("answerC");
		String answerD=request.getParameter("answerD");
		String correctAnswer=request.getParameter("correctAnswer");
		String score=request.getParameter("score");
		String questionID=request.getParameter("questionID");
		if(questionName==null||questionName.equals("")||answerA==null||
				answerA.equals("")||answerB==null||answerB.equals("")||
				answerC==null||answerC.equals("")||answerD==null||answerD.equals("")||
				correctAnswer==null||correctAnswer.equals("")||score==null||score.equals("")){
								response.sendRedirect("error.jsp");
								return;
							}
 %>
								
    <form method="post"  action="modifyQuestion">
    <input type="hidden" name="questionID" value=<%=questionID %>><br> <br />
		请输入题目内容： <input type="text" name="questionName" value=<%=questionName %>><br> <br />
		请输入A选项内容： <input type="text" name="answerA" value=<%=answerA %>><br> <br />
		请输入B选项内容： <input type="text" name="answerB" value=<%=answerB %>><br> <br />
		请输入C选项内容： <input type="text" name="answerC" value=<%=answerC %>><br> <br />
		请输入D选项内容： <input type="text" name="answerD" value=<%=answerD %>><br> <br />
		请选择正确选项： <input type="radio" name="correctAnswer" value="1">A<br>
		<input type="radio" name="correctAnswer" value="2">B<br>
		<input type="radio" name="correctAnswer" value="3">C<br>
		<input type="radio" name="correctAnswer" value="4">D<br>
		请输入该题的分值： <input type="text" name="score" value=<%=score %>><br> <br /> 
		<input
			type="submit" name="submit" value="修改"><br> <br /> <br />
	 </form>
<%
	}
	else {
	session.invalidate();
	response.sendRedirect("index.jsp");
   }
%>
</body>
</html>