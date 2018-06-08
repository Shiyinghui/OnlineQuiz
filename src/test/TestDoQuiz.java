package test;

import com.system.service.*;
import com.system.entity.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map;
import java.util.Set;

public class TestDoQuiz {

    public static void main(String[] args){
        Gamer g = new Gamer();
        Test t = new Test();
        QLibrary q =new QLibrary();
        TestService ts = new TestService();


        // 构造Test对象时已初始化 分数=0、时间，ID=-1L;
        // 开始一场测试
        g.setId(1L);
        q.setId(1L);
        long id = ts.beginTest(t,q,g);
        System.out.println(id);

        // 测试 通过id得到一次quiz的详细记录
      /*  t.setTestID(id);
        t = ts.getTestRecord(t);
        System.out.println(t.getTestID());
        System.out.println(t.getTestTime());
        System.out.println(t.getTestScore());*/

        // 测试 由玩家id得到此玩家所做的所有quiz(test)
       /* g.setId(1L);
        Vector<Test> v = new Vector<>();
        v = ts.getTestRecord(g);
        Iterator<Test> it = v.iterator();
        while(it.hasNext()){
            Test t1 = new Test();
            t1 = it.next();
            System.out.println(t1.getTestID());
            System.out.println(t1.getTestTime());
            System.out.println(t1.getTestScore());
        }*/

       // 测试 由测试ID得到 所属题库和管理员
       /* t.setTestID(2);
        Map<QLibrary,Administrator> map = new HashMap<>();
        map = ts.getDetailOfTest(t);
        Set<QLibrary> keySet = map.keySet();
        Iterator<QLibrary> it = keySet.iterator();
        while(it.hasNext()){

            QLibrary ql = it.next();
            Administrator admin = (Administrator) map.get(ql);
            System.out.println(ql.getId());
            System.out.println(admin.getId());
        }*/

        // 测试  由题库ID得到所有的测试信息
         /*q.setId(1);
         Map<Test, Gamer> map1 = new HashMap<>();
         map1 = ts.getTestRecord(q);
         Set<Test> keySet1 = map1.keySet();
         Iterator<Test> it1 = keySet1.iterator();
         while(it1.hasNext()){
             t = it1.next();
             g = (Gamer) map1.get(t);
             System.out.println(t.getTestID());
             System.out.println(g.getId());
         }*/

    }
}
