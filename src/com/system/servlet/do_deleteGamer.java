package com.system.servlet;

import com.system.entity.Gamer;
import com.system.entity.Administrator;
import com.system.service.deleteGService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class do_deleteGamer extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int isDelete = 0;
        HttpSession session = request.getSession();
        Administrator admin = (Administrator) session.getAttribute("admin");
        String type = (String)session.getAttribute("type");
        boolean state =(boolean)session.getAttribute("state");
        if (!session.isNew()) {
            if (session == null || session.getAttribute("admin") == null
                    || (Boolean) session.getAttribute("state") == false) {
                session.invalidate();
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                session.setAttribute("admin",admin);
                session.setAttribute("type","admin");
                session.setAttribute("state",true);
                String gEmails[] = request.getParameterValues("deleteGamer");
                Gamer tempG = new Gamer();
                if (gEmails != null && gEmails.length >= 0) {

                    for (int i = 0; i < gEmails.length; i++) {
                        tempG.setEmail(gEmails[i]);
                        boolean b = new deleteGService().delete(tempG);
                        if (b) {
                          //  response.sendRedirect("Admin/viewGamer.jsp");
                            isDelete++;
                           // System.out.println(isDelete);
                        } else {
                            response.sendRedirect("error.jsp");
                        }
                    }
                    if(isDelete == gEmails.length)
                   // request.getRequestDispatcher("/Admin/viewGamer.jsp").forward(request, response);
                        response.sendRedirect("viewGamer.jsp");

                } else {
                    System.out.println("系统错误");
                    request.getRequestDispatcher("/Admin/AdminHome.jsp").forward(request, response);
                }
            }
        } else {
            session.invalidate();
            response.sendRedirect("error.jsp");
        }

    }
}
