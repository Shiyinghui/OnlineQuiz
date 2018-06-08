package com.system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.system.entity.Administrator;
import com.system.service.RegisterService;

public class do_adminRegister extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req,resp);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 管理员注册
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
       // String sex=request.getParameter("sex");
        String phone=request.getParameter("phone");
        if(email==null||password==null||name==null||phone==null||email.equals("")||password.equals("")||name.equals("")||phone.equals("")){
            response.sendRedirect("error.jsp");
            System.out.println("啥都没有");
            return;
        }
        Administrator admin=new Administrator();
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setGender("0");
        /*if(sex.equals("男")){
            admin.setGender("0");
        }else {
            admin.setGender("1");
        }*/
        System.out.println(email);
        System.out.println(password);
        System.out.println(name);

        admin.setPhone(phone);
        admin.setName(name);
        boolean b=new RegisterService().AdminRegister(admin);
        if(b){
            response.sendRedirect("registerSuccess.jsp");
        }
        else{
            response.sendRedirect("error.jsp");
        }

    }

}
