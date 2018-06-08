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

public class do_deleteQ extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 删除问题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Administrator admin = (Administrator) session.getAttribute("admin");
        if (!session.isNew()) {
            if (session == null || session.getAttribute("admin") == null
                    || (Boolean) session.getAttribute("state") == false) {
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
            else{
                session.setAttribute("admin",admin);
                session.setAttribute("type","admin");
                session.setAttribute("state",true);
                String questionID=request.getParameter("questionID");
                System.out.println("questionID="+questionID);
                Question question=new Question();
                question.setId(Long.parseLong(questionID));
                if(questionID!=null&&!questionID.equals("")){
                    boolean flag=new QuestionService().deleteAQuestion(question);
                    if(flag){
                        System.out.println("删除成功！");
                 //		response.sendRedirect("teacherIndex.jsp");
                        request.getRequestDispatcher("/Admin/manageQuestion.jsp").forward(request, response);
                    }
                    else{
                        System.out.println("删除失败！");
                        //						response.sendRedirect("teacherIndex.jsp");
                        request.getRequestDispatcher("/Admin/AdminHome.jsp").forward(request, response);
                    }
                }
                else{
                    session.invalidate();
                    //					response.sendRedirect("index.jsp");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);

                }


            }
        }

    }
}
