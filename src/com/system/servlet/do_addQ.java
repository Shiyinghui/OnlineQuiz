package com.system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.entity.Administrator;
import com.system.entity.Question;
import com.system.entity.QLibrary;
import com.system.service.QuestionService;

public class do_addQ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // 增加一个问题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        QLibrary currentQL = (QLibrary) session.getAttribute("currentQL");
        Administrator admin = (Administrator)session.getAttribute("admin");
        String questionName=request.getParameter("questionName");
        String answerA=request.getParameter("answerA");
        String answerB=request.getParameter("answerB");
        String answerC=request.getParameter("answerC");
        String answerD=request.getParameter("answerD");
        String correctAnswer=request.getParameter("correctAnswer");
        String score=request.getParameter("score");
        if(questionName==null||questionName.equals("")||answerA==null||
                answerA.equals("")||answerB==null||answerB.equals("")||
                answerC==null||answerC.equals("")||answerD==null||answerD.equals("")||
                correctAnswer==null||correctAnswer.equals("")||score==null||score.equals("")){
            response.sendRedirect("error.jsp");
            return;
        }
        else{
            Question question=new Question();
            question.setTitle(questionName);
            question.setChoiceA(answerA);
            question.setChoiceB(answerB);
            question.setChoiceC(answerC);
            question.setChoiceD(answerD);
            question.setCorrectAnswer(Integer.parseInt(correctAnswer));
            question.setScore(Integer.parseInt(score));
            boolean flag=new QuestionService().addQuestionToQL(question, currentQL);
            if(flag){
                session.setAttribute("admin",admin);
                session.setAttribute("state",true);
                session.setAttribute("type","admin");
                System.out.println("添加成功！");
                request.getRequestDispatcher("/Admin/getAllQLibrary.jsp").forward(request, response);
            }
            else{
                System.out.println("添加失败！");
                request.getRequestDispatcher("/Admin/AdminHome.jsp").forward(request, response);
            }
        }

    }

}

