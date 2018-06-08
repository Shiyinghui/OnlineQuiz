package com.system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.entity.Announce;
import com.system.entity.Administrator;
import com.system.service.AnnounceService;
import com.system.util.TimeUtil;

public class do_addAnnounce extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       // 新发布一条公告
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (!session.isNew()) {
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Administrator admin = (Administrator)session.getAttribute("admin");
                if (title != null && content != null && !title.equals("") && !content.equals("")) {
                    TimeUtil ti = new TimeUtil();
                    String time = ti.getTime();
                    Announce notice = new Announce();
                    notice.setContent(content);
                    notice.setTitle(title);
                    notice.setTime(time);
                    boolean flag = new AnnounceService().addAnnounce(notice);
                    if (flag) {
                        session.setAttribute("admin",admin);
                        session.setAttribute("state",true);
                        session.setAttribute("type","admin");
                        System.out.println("发布成功");
                        request.getRequestDispatcher("/Admin/viewAnnounce.jsp").forward(request, response);
                    } else {

                        System.out.println("发布失败");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);

                    }
                } else {
                    System.out.println("系统繁忙，请稍候重试");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }
        }
        else {
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
}
