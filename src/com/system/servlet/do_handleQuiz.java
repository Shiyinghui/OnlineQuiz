package com.system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.entity.*;
import com.system.service.AnswerService;
import com.system.service.QuestionService;
import com.system.service.CheckTestService;
import com.system.util.RandomManage;


public class do_handleQuiz extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Gamer gamer =(Gamer)session.getAttribute("gamer");
        PrintWriter writer=resp.getWriter();
        if(req.getParameter("qlID")==null||req.getParameter("testID")==null||req.getParameter("gamerID")==null||req.getParameter("idStr")==null){
            //resp.setContentType("plain/text");
            writer.println("参数异常");
            return;
        }
        /*
         * 获得所需的ID参数
         */
        long qlID=Long.parseLong(req.getParameter("qlID"));
        long testID=Long.parseLong(req.getParameter("testID"));
        long gamerID=Long.parseLong(req.getParameter("gamerID"));

        QLibrary ql=new QLibrary();
        Test test=new Test();
       // Gamer gamer=new Gamer();
        gamer.setId(gamerID);
        test.setTestID(testID);
        ql.setId(qlID);
        Vector<Question>allQuestions = new QuestionService().getAllQuestionOfQL(ql);//获取题库中的所有题目
        List<Question>ansQuestionList = new ArrayList<Question>();//定义已经回答了的题目
        Map<Question,Answer>answerMap = new HashMap<Question,Answer>();

        if(allQuestions.size()>0){
            List<Long>idList=new RandomManage().deCoding(req.getParameter("idStr"));
            //获得这次考试考的题目
            for(Question question:allQuestions){
                for(long id:idList){
                    if(question.getId()==id){
                        ansQuestionList.add(question);
                    }
                }
            }
            //获得这次考试的回答
            for(Question question:ansQuestionList){
                String answer=req.getParameter(String.valueOf(question.getId()));
                Answer tempanswer=new Answer();
                if(answer!=null){
                    try {
                        tempanswer.setAnswerContent(Integer.parseInt(answer));
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        tempanswer.setAnswerContent(0);
                    }
                }else{
                    tempanswer.setAnswerContent(0);
                }
                answerMap.put(question, tempanswer);
            }
            //提交至数据库
            boolean flag=new AnswerService().addAnswer(test, answerMap);
            if(flag){

                //计算得分
                int score = new CheckTestService().checkTest(test);
                session.setAttribute("gamer",gamer);
                session.setAttribute("type","gamer");
                session.setAttribute("state",true);
                session.setAttribute("testID",testID);
                session.setAttribute("score",score);
                //session.setAttribute("answerMap",answerMap);
                resp.sendRedirect("quizScore.jsp");
            }else{
                //resp.setContentType("plain/text");
                writer.println("将answer加入数据库异常");
                writer.flush();
            }

        }else{
            //resp.setContentType("plain/text");
            writer.println("没有题目");
            return;
        }

    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        doPost(req, resp);
    }

}
