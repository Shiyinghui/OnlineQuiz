package com.system.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.entity.QLibrary;
import com.system.entity.Administrator;
import com.system.service.QLibraryService;
import com.system.util.TimeUtil;

public class do_QLibrary extends HttpServlet{
    public String DateComplianceConvert(String ISODate)
    {
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date dt = null;
        try {
            dt = sd1.parse(ISODate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sd2.format(dt);
        System.out.println(newDate);
        return newDate;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        if(!session.isNew()){
            Administrator admin=(Administrator) session.getAttribute("admin");
            if(admin!=null){
                String bankName=request.getParameter("bankName");
                String BankType=request.getParameter("bankType");
                String startTime=request.getParameter("startTime");
                String endTime=request.getParameter("endTime");
                String amount=request.getParameter("amount");


                if(BankType==null||admin==null||startTime==null||endTime==null||admin.equals("")||BankType.equals("")||startTime.equals("")||endTime.equals("")){
                    response.sendRedirect("error.jsp");
                    return;
                }
                else{
                    session.setAttribute("admin",admin);
                    session.setAttribute("state",true);
                    session.setAttribute("type","admin");
                    QLibrary adminBank=new QLibrary();
                    adminBank.setName(bankName);
                    adminBank.setType(BankType);
                    new TimeUtil();
                    adminBank.setStartTime(DateComplianceConvert(startTime));
                    adminBank.setEndTime(DateComplianceConvert(endTime));
                    adminBank.setAmount(Integer.parseInt(amount));
                    //		session.setAttribute("currentTeacherSpace",teacherBank);
                    boolean flag=new QLibraryService().addQLibrary(adminBank, admin);
                    if(flag){
                        System.out.println("添加题库成功！");
                        request.getRequestDispatcher("/Admin/getAllQLibrary.jsp").forward(request, response);
                    }
                    else{
                        System.out.println("添加题库失败！");
                        request.getRequestDispatcher("/Admin/AdminHome.jsp").forward(request, response);
                    }

                }
            }
            else{
                session.invalidate();
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        }
        else{
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }


    }

}
