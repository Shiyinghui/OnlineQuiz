package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.entity.Gamer;
import com.system.entity.Administrator;
import com.system.service.RegisterService;
import com.system.service.ConsultService;

public class TestRegister {


    public static void main(String[] args) {

        // 管理员注册测试，测试了 注册方法，通过邮箱获取管理员方法
        Administrator admin = new Administrator();
        admin.setEmail("a4@test.com");
        admin.setGender("0");
        admin.setPhone("18018018018");
        admin.setName("admin4");
        admin.setPassword("123456");
       boolean b1 = new RegisterService().AdminRegister(admin);
       if(b1){
           Administrator a = new Administrator();
           a.setEmail("a4@test.com");
           a = new ConsultService().getAdmin(a);
           System.out.println(a.getId());
           System.out.println(a.getEmail());
           System.out.println(a.getPassword());
           System.out.println(a.getName());
           System.out.println(a.getGender());
           System.out.println(a.getPhone());

       }
       else System.out.println("false");

       // 玩家注册测试，测试了 注册方法，通过邮箱获取玩家方法
        Gamer gamer = new Gamer();
        gamer.setEmail("g5@test.com");
        gamer.setPassword("123456");
        gamer.setName("Gamer5");
        gamer.setGender("0");
        boolean b2 = new RegisterService().GamerRegister(gamer);
        if(b2){
            Gamer g = new Gamer();
            g.setEmail("g5@test.com");
            g = new ConsultService().getGamer(g);
            System.out.println(g.getId());
            System.out.println(g.getEmail());
            System.out.println(g.getName());
            System.out.println(g.getPassword());
            System.out.println(g.getGender());
        }

    }



}
