package test;

import com.system.entity.Administrator;
import com.system.entity.Gamer;
import com.system.service.LoginService;

public class TestLogin {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //  登录测试，玩家登录
        Gamer g=new Gamer();
        g.setEmail("g0@test.com");
        g.setPassword("123456");
        boolean b1= new LoginService().gamerLogin(g);
        System.out.println(b1);
        if(b1)
        {
            System.out.println(123);
        }

        //  管理员登录
        Administrator a=new Administrator();
        a.setEmail("a0@test.com");
        a.setPassword("123456");
        boolean b2 = new LoginService().adminLogin(a);
        System.out.println(b2);
        if(b2) System.out.println(1234);

    }

}
