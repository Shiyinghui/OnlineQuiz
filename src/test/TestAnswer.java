package test;

import com.system.entity.*;
import com.system.service.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class TestAnswer {

    public static void main(String[] args){

        AnswerService anss = new AnswerService();
        Test test = new Test();
        test.setTestID(1L);
        QLibrary ql = new QLibrary();
        ql.setId(1L);
        Map<Question,Answer> map = new HashMap<>();

        // 构造答案对象时，已初始化 time, score = 0; isChecked = false
      /*  for(long i=1L; i<25;i++){
            Answer ans = new Answer();
            ans.setAnswerContent(1);  // 测试，所有选项都设置为A
            Question ques = new Question();
            ques.setId(i);
            map.put(ques,ans);
            }
         //测试  添加答案
        anss.addAnswer(test,map);*/

      // 测试 添加一个答案
       Question ques1 = new Question();
       Answer ans1 = new Answer();
       ans1.setAnswerContent(1);
       ques1.setId(25);
       anss.addAnswer(test,ques1,ans1);



    }
}
