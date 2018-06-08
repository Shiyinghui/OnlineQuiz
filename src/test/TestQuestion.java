package test;

import com.system.entity.*;
import com.system.service.*;

import java.util.Vector;

public class TestQuestion {

    public static void main(String[] args){


        //测试获取一个题库的所有题目
       Vector<Question> v = new Vector<>();
       QLibrary ql = new QLibrary();
       ql.setId(1L);
       v = new QuestionService().getAllQuestionOfQL(ql);
       System.out.println(v.size());
       Question ques = new Question();
       QuestionService qs = new QuestionService();

       // 测试修改一个问题, 首先输入问题的详细信息,
       /* ques = v.get(1);
        System.out.println(ques.getId());
        System.out.println(ques.getTitle());
        System.out.println(ques.getChoiceA());
        System.out.println(ques.getChoiceB());
        System.out.println(ques.getChoiceC());
        System.out.println(ques.getChoiceD());
        System.out.println(ques.getCorrectAnswer());
        System.out.println(ques.getScore());

        ques.setChoiceD("秋去冬来");
        qs.updateQuestion(ques);
        v = qs.getAllQuestionOfQL(ql);
        ques = v.get(1);
        System.out.println(ques.getChoiceD());*/


       // 测试删除一个问题
       /* ques = v.get(23);
        qs.deleteAQuestion(ques);
        v = qs.getAllQuestionOfQL(ql);
        System.out.println(v.size());*/

        // 测试 向题库新增一个问题
        ques.setTitle("联想题：1，扇子 2，耳朵 3，孔明");
        ques.setChoiceA("智");
        ques.setChoiceB("天");
        ques.setChoiceC("风");
        ques.setChoiceD("云");
        ques.setCorrectAnswer(3);
        ques.setScore(1);
        qs.addQuestionToQL(ques,ql);
        v = qs.getAllQuestionOfQL(ql);
        System.out.println(v.size());

       // 测试 删除一个题库的所有问题
       // qs.deleteAllQuestionOfQL(ql);
    }
}
