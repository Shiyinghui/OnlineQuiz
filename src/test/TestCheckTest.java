package test;

import java.util.Map;
import java.util.HashMap;
import com.system.service.*;
import com.system.entity.*;

public class TestCheckTest {

    public static void main(String[] args){

        // 测试取出所有答案
        AnswerService anss = new AnswerService();
        Map<Question,Answer> map = new HashMap<>();
        Test t = new Test();
        t.setTestID(1L);
        map = anss.getAnswer(t);
       /* for (Map.Entry<Question, Answer> entry : map.entrySet()) {
             System.out.print(entry.getKey().getId()+" ");
             System.out.println(entry.getValue().getAnswerID());
        }*/

        // 批改 一个test
        CheckTestService cts = new CheckTestService();
        int score = cts.checkTest(t);
        System.out.println(score);
    }
}
