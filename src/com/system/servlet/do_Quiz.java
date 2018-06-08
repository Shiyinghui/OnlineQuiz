package com.system.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.entity.*;
import com.system.service.QuestionService;
import com.system.service.AnswerService;


public class do_Quiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if(!session.isNew()){
            QLibrary currentQL=(QLibrary) session.getAttribute("currentQL");
            Test testInstance=(Test)session.getAttribute("testInstance");
            if(currentQL==null||testInstance==null){
                session.invalidate();
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                Vector<Question> questionLists = new QuestionService().getAllQuestionOfQL(currentQL);
                if(questionLists!=null){
                    Iterator<Question> iterList = questionLists.iterator();
                    Map<Question, Answer> answerMap=new HashMap<Question, Answer>();
                    int number=0;
                    while (iterList.hasNext()) {
                        Question question = iterList.next();
                        String temp=request.getParameter(Integer.toString(number+1));
                        if(temp==null){
                            temp="0";
                        }
                        Answer answer=new Answer();
                        answer.setAnswerContent(Integer.parseInt(temp));
                        answerMap.put(question, answer);
                        number++;
                    }
                    boolean flag=new AnswerService().addAnswer(testInstance, answerMap);
                    if(flag){
                        System.out.println("提交成功！");
                        request.getRequestDispatcher("/Gamer/GamerHome.jsp").forward(request, response);
                    }
                    else{
                        System.out.println("提交失败！");
                        request.getRequestDispatcher("/Gamer/GamerHome.jsp").forward(request, response);
                    }
                }
                else{
                    System.out.println("请求出错，请稍候重试！");
                    request.getRequestDispatcher("/Gamer/GamerHome.jsp").forward(request, response);
                }

            }
        }
        else{
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

}
