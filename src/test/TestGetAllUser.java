package test;

import java.util.Iterator;
import java.util.List;

import com.system.entity.Administrator;
import com.system.entity.Gamer;
import com.system.service.ConsultService;

public class TestGetAllUser {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // 测试 获得系统所有的管理员，得到每一个管理员的所有信息
        List<Administrator> adminList=new ConsultService().getAllAdmins();
        Iterator<Administrator> adminIterator=adminList.iterator();
        while(adminIterator.hasNext()){
            Administrator admin= adminIterator.next();
            System.out.println(admin.getId());
            System.out.println(admin.getEmail());
            System.out.println(admin.getPassword());
            System.out.println(admin.getName());
            System.out.println(admin.getPhone());
            System.out.println(admin.getGender());
            System.out.println();

        }

        // 测试 获得系统所有的玩家，得到每一个玩家的所有信息
       List<Gamer> gamerList = new ConsultService().getAllGamers();
        Iterator<Gamer> gamerIterator= gamerList.iterator();
        while(gamerIterator.hasNext()){
            Gamer gamer = gamerIterator.next();
            System.out.println(gamer.getId());
            System.out.println(gamer.getEmail());
            System.out.println(gamer.getPassword());
            System.out.println(gamer.getName());
            System.out.println(gamer.getGender());
            System.out.println();


        }

    }

}
