package com.system.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.entity.Administrator;
import com.system.entity.Gamer;
import com.system.service.LoginService;
import com.system.service.ConsultService;


public class do_login extends HttpServlet{
    public do_login() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if(!session.isNew()){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String identity = request.getParameter("identity");
            if (username == null || password == null || identity == null || username.contentEquals("")
                    || password.contentEquals("") || identity.contentEquals("")) {
                response.sendRedirect("error.jsp");
                return;
            }
            if (identity.equals("用户")) {

                Gamer gamer = new Gamer();
                gamer.setEmail(username);
                gamer.setPassword(password);
                boolean b = new LoginService().gamerLogin(gamer);
                // System.out.println(b);
                if (b) {
                    gamer = new ConsultService().getGamer(gamer);
                    System.out.println("登陆成功");
                    session.setAttribute("gamer", gamer);
                    session.setAttribute("state", true);
                    session.setAttribute("type", "gamer");
                    response.sendRedirect("Gamer/GamerHome.jsp");
                    return;
                } else {
                    response.sendRedirect("error.jsp");
                    session.invalidate();
                    return;
                }

            } else if (identity.equals("管理员")) {
                Administrator admin = new Administrator();
                admin.setEmail(username);
                admin.setPassword(password);
                boolean b = new LoginService().adminLogin(admin);
                if (b) {

                    session.setAttribute("admin", admin);
                    session.setAttribute("state", true);
                    session.setAttribute("type", "admin");
                    response.sendRedirect("Admin/AdminHome.jsp");
                    return;
                } else {
                    response.sendRedirect("error.jsp");
                    session.invalidate();
                    return;
                }

            }

        }
        else{
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }


}
