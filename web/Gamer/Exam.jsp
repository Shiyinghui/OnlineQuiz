<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.system.service.*"%>
<%@page import="java.util.*"%>
<%@ page import="com.system.util.TimeUtil" %>
<%@ page import="com.system.service.TestService" %>
<%@ page import="com.system.entity.Question" %>
<%@ page import="com.system.util.RandomManage" %>
<%@ page import="com.system.entity.Gamer" %>
<%@ page import="com.system.entity.Test" %>
<%@ page import="com.system.entity.QLibrary" %>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>


<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String type = (String) session.getAttribute("type");
	Gamer g = (Gamer) session.getAttribute("gamer");
	boolean flag = (boolean) session.getAttribute("state");
	if (flag && type.equals("gamer")) {
		session.setAttribute("gamer",g);
		session.setAttribute("type","gamer");
		session.setAttribute("state",true);
		QLibrary ql=new QLibrary();
		ql.setId(Long.parseLong(request.getParameter("qlID")));
		ql.setName(request.getParameter("qlName"));
		ql.setAmount(Integer.parseInt(request.getParameter("amount")));
		ql.setType(request.getParameter("qlType"));
		ql.setStartTime(request.getParameter("startTime"));
		ql.setEndTime(request.getParameter("endTime"));
		Vector<Question> quesv = new QuestionService().getAllQuestionOfQL(ql);
		if(quesv.size()==0){
		    out.println("很抱歉，当前题库没有题，不能答题，换个题库吧~");
			return;
		}


		Test t = new Test();
		TimeUtil ti = new TimeUtil();
		String testTime = ti.getTime();
		java.sql.Date myTestTime=ti.parseStringToDate(testTime);
		t.setTestTime(testTime);
		long testID = new TestService().beginTest(t, ql, g);//准备开始测试，向服务器发送请求，返回测试ID
		t.setTestID(testID);
		if(testID!=-1L){
%>
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
    <link href="../css/exam.css" rel="stylesheet" type="text/css">

<style type="text/css">
input[type="radio"] {
	font-size: 100px;
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
                    <li>
                        <a href="AnnounceBoard.jsp"><i class="fa fa-cogs fa-fw"></i>公告板</a>
                    </li>
                    <li class="active">
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

		<div class="content" style="margin-bottom: 100px;">

			<div class="container">
				<div class="row">

					<div class="col-xs-2" style="padding-right: 0px;padding-bottom:15px;">
						<div class="def-bk" id="bk-exam-control">

							<div class="def-bk-content" id="exam-control">

								<div id="question-time" class="question-time-normal">
									<div style="height:140px;text-align: center;">
										<i id="time-icon" class="fa fa-clock-o"> </i>
									</div>

									  <div>
									    <span id="t_h">00时</span>
									    <span id="t_m">00分</span>
									    <span id="t_s">00秒</span>
									  </div>
									<script>
										var end;
									  	var EndTime= new Date();
										end = EndTime.getTime()+60*60*1000;
									  function GetRTime(){
									    var NowTime = new Date();
									    var t =end - NowTime.getTime();
									    var h=0;
									    var m=0;
									    var s=0;
									    if(t>=0){
									      h=Math.floor(t/1000/60/60%24);
									      m=Math.floor(t/1000/60%60);
									      s=Math.floor(t/1000%60);
									    }

									    document.getElementById("t_h").innerHTML = h + "时";
									    document.getElementById("t_m").innerHTML = m + "分";
									    document.getElementById("t_s").innerHTML = s + "秒";
									  }
									  setInterval(GetRTime,0);
									</script>

								</div>

								<div id="exam-info" style="display:none;">
									<span id="answer-hashcode"></span>
								</div>
							</div>

						</div>

					</div>
					<div class="col-xs-10" style="padding-right: 0px;">
						<div class="def-bk" id="bk-exampaper">

							<div class="expand-bk-content" id="bk-conent-exampaper">
								<div id="exampaper-header">
									<div id="exampaper-title">
										<i class="fa fa-send"></i>
										<span id="exampaper-title-name"> <%=ql.getName() %> </span>
										<span style="display:none;" id="exampaper-id">1</span>
									</div>
									<div id="exampaper-desc-container">
										<div id="exampaper-desc" class="exampaper-filter">

										</div>
									</div>

								</div>

								<form class="question-body" action="handleQuiz" method="post">
								<%
								Vector<Question> qv = new QuestionService().getAllQuestionOfQL(ql);
								if(qv!=null && qv.size()>=ql.getAmount() && qv.size()>0 && ql.getAmount()>0){

									List<Integer>rdmList=new RandomManage().getRandom(ql.getAmount(), qv.size());

									if(rdmList!=null){
										//将题目内容加入这个list中
										List<Question> questionList=new ArrayList<Question>();
										List<Long>idList=new ArrayList<Long>();

										for(int rdm:rdmList){
											questionList.add(qv.get(rdm-1));
											idList.add(qv.get(rdm-1).getId());
										}
										//准备循环插入题目:

										Iterator<Question>iter=questionList.iterator();
										int counter=0;
										while(iter.hasNext()){
											counter++;
											Question tempQ =iter.next();
								%>
								<ul id="exampaper-body">
									<li class="question qt-singlechoice">
										<div class="question-title">
											<div class="question-title-icon"></div>
											<span class="question-no"><%=counter %></span>
											<span class="question-type" style="display: none;">singlechoice</span>
											<span class="knowledge-point-id" style="display: none;">0</span>
											<span class="question-type-id" style="display: none;">1</span>
											<span>[单选题]</span>
											<span class="question-point-content">
											<span>(</span>
											<span class="question-point"><%=tempQ.getScore() %></span>
											<span>分)</span></span>
											<span class="question-id" style="display:none;">70</span>
										</div>

											<p class="question-body-text"><%=tempQ.getTitle() %></p>
											<ul class="question-opt-list">
												<li class="question-list-item"><input type="radio" value="1" name=<%=tempQ.getId() %> class="question-input">A: <%=tempQ.getChoiceA() %></li>
												<li class="question-list-item"><input type="radio" value="2" name=<%=tempQ.getId() %> class="question-input">B: <%=tempQ.getChoiceB() %></li>
												<li class="question-list-item"><input type="radio" value="3" name=<%=tempQ.getId() %> class="question-input">C: <%=tempQ.getChoiceC() %></li>
												<li class="question-list-item"><input type="radio" value="4" name=<%=tempQ.getId() %> class="question-input">D: <%=tempQ.getChoiceD() %></li>

											</ul>

									</li>
								</ul>
								<%}//System.out.println("error1") ;%>





								<div id="question-submit">
									<button class="btn-success btn" style="width:100%;" type="submit">
										我要交卷
									</button>
								</div>
								<!-- 隐藏ID的提交处 -->
								<input 	type="hidden" name="qlID" value=<%=ql.getId() %>>
								<input  type="hidden" name="gamerID" value=<%=g.getId() %>>
								<input 	type="hidden" name="testID"	value=<%=t.getTestID() %>>
								<%String idStr=new RandomManage().encoding(idList); %>
								<input  type="hidden" name="idStr" value=<%=idStr %>>

								<%} //System.out.println("error3") ;%>
								<%} //System.out.println("error2") ;%>
								</form>
								<div id="exampaper-footer">
									<div id="question-navi">
										<div id="question-navi-controller">
											<i class="fa fa-arrow-circle-up"></i>
											<span>答题卡</span>
										</div>
										<div id="question-navi-content">
										</div>
									</div>

								</div>
							</div>

						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="footer"></div>

		<script type="text/javascript" src="../js/all.js?v=0712"></script>
		<%--<script type="text/javascript" src="../js/paper-examing.js"></script>--%>
	</body>
<%
	}
		}
	else {
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
%>
</html>
