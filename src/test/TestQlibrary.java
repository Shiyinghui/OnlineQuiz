package test;

import com.system.entity.*;
import com.system.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class TestQlibrary {

    public static void main(String[] args){

        // 测试 添加题库
        Administrator admin = new Administrator();
        admin.setEmail("a0@test.com");
        admin = new ConsultService().getAdmin(admin);
        QLibrary ql = new QLibrary();
        ql.setName("文学类知识小测~");
        ql.setType("文学类");
        ql.setStartTime("2018-5-21 20:15:10");
        ql.setEndTime("2018-5-21 20:20:00");
        ql = new QLibraryService().addQLToGetId(ql,admin);
        System.out.println(ql.getId());

        // 测试 获取一个管理员发布的所有题库
        List<QLibrary> l = new ArrayList<>();
        l = new QLibraryService().getQLibrary(admin);
        Iterator<QLibrary> it = l.iterator();
        while(it.hasNext()){
            QLibrary QL= new QLibrary();
            QL = it.next();
            System.out.println(QL.getId());
            System.out.println(QL.getStartTime());
            System.out.println(QL.getEndTime());
            System.out.println(QL.getName());
            System.out.println(QL.getType());
            System.out.println(QL.getAmount());
        }
    }
}
