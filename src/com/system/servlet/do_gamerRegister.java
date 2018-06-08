package com.system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.system.entity.Gamer;
import com.system.service.RegisterService;

public class do_gamerRegister extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req,resp);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 玩家注册
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        //String sex=request.getParameter("sex");
        if(email==null||password==null||name==null||email.equals("")||password.equals("")||name.equals("")){
            response.sendRedirect("error.jsp");
            return;
        }
        Gamer gamer=new Gamer();
        gamer.setEmail(email);
        gamer.setGender("0");
        /* if(sex.equals("男")){
            gamer.setGender("0");
        }
        else{
            gamer.setGender("1");
        }*/
        gamer.setPassword(password);
        gamer.setName(name);
        boolean b=new RegisterService().GamerRegister(gamer);
        if(b){
            response.sendRedirect("registerSuccess.jsp");
        }
        else{
            response.sendRedirect("error.jsp");
        }
    }

}
