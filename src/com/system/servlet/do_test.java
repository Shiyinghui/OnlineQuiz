/*package com.system.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.system.entity.*;
import com.system.service.AnswerService;
import com.system.service.QuestionService;

public class do_test extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if(!session.isNew()){
            QLibrary currentQL=(QLibrary) session.getAttribute("currentQL");
            int array[]=(int[])session.getAttribute("array");
            Test testInstance=(Test)session.getAttribute("testInstance");
            if(currentQL==null||array==null||testInstance==null){
                session.invalidate();
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                Vector<Question> questionLists = new QuestionService().getAllQuestionOfQL(currentQL);
                if(questionLists!=null){
                    Map<Question, Answer> answerMap=new HashMap<Question, Answer>();
                    for(int a=0;a<25;a++){
                        Question question = questionLists.get(array[a]);
                        String temp=request.getParameter(String.valueOf(question.getId()));
                        Answer answer =new Answer();
                        if(temp==null){
                            temp="0";
                        }
                        answer.setAnswerContent(Integer.parseInt(temp));
                        answerMap.put(question, answer);
                    }
                    boolean flag=new AnswerService().addAnswer(testInstance, answerMap);
                    if(flag){
                        System.out.println("提交成功！");
                        request.getRequestDispatcher("/gamerIndex.jsp").forward(request, response);
                    }
                    else{
                        System.out.println("提交失败！");
                        request.getRequestDispatcher("/gamerIndex.jsp").forward(request, response);
                    }
                }
                else{
                    System.out.println("请求出错，请稍候重试！");
                    request.getRequestDispatcher("/gamerIndex.jsp").forward(request, response);
                }
            }
        }
        else{
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
}*/
