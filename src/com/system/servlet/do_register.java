package com.system.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class do_register extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String type=request.getParameter("type");
        if(type==null||type.equals("")){
            response.sendRedirect("error.jsp");
        }else{
            if(type.equals("admin")){
                request.getRequestDispatcher("/adminRegister").forward(request, response);

            }else if(type.equals("gamer")){
                request.getRequestDispatcher("/gamerRegister").forward(request, response);
            }else{
                response.sendRedirect(request.getContentType()+"error.jsp");
            }
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req,resp);
    }
}
