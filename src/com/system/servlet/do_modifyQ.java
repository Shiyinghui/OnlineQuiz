package com.system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.entity.Administrator;
import com.system.entity.Question;
import com.system.service.QuestionService;

public class do_modifyQ extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 修改问题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String questionName=request.getParameter("questionName");
        String answerA=request.getParameter("answerA");
        String answerB=request.getParameter("answerB");
        String answerC=request.getParameter("answerC");
        String answerD=request.getParameter("answerD");
        String correctAnswer=request.getParameter("correctAnswer");
        String score=request.getParameter("score");
        String questionID=request.getParameter("questionID");
        Administrator admin = (Administrator)session.getAttribute("admin");
        if(questionName==null||questionName.equals("")||answerA==null||
                answerA.equals("")||answerB==null||answerB.equals("")||
                answerC==null||answerC.equals("")||answerD==null||questionID==null||answerD.equals("")||
                correctAnswer==null||correctAnswer.equals("")||score==null||score.equals("")
                ||questionID.equals("")){
            response.sendRedirect("error.jsp");
            return;
        }
        else{
            session.setAttribute("admin",admin);
            session.setAttribute("state",true);
            session.setAttribute("type","admin");
            Question question=new Question();
            question.setId(Long.parseLong(questionID));
            question.setTitle(questionName);
            question.setChoiceA(answerA);
            question.setChoiceB(answerB);
            question.setChoiceC(answerC);
            question.setChoiceD(answerD);
            question.setCorrectAnswer(Integer.parseInt(correctAnswer));
            question.setScore(Integer.parseInt(score));
            boolean flag=new QuestionService().updateQuestion(question);
            if(flag){
                System.out.println("修改成功！");
                //				response.sendRedirect("teacherIndex.jsp");

                request.getRequestDispatcher("/Admin/manageQuestion.jsp").forward(request, response);
            }
            else{
                response.sendRedirect("error.jsp");
                System.out.println("修改失败！");
            }
        }


    }
}
