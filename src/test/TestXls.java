package test;

import java.io.File;
import java.util.Vector;
import com.system.util.XlsUtil;
import com.system.entity.*;
import com.system.service.*;


public class TestXls {


     // 已测试 生活常识类、娱乐类、文学类、天文地理类、体育文化类
     //  都导入成功
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        XlsService svs=new XlsService();
        File file=new File("D:/test/文学类.xls");
        Vector<Question> v=svs.resolveXls(file);
        System.out.println(v==null);
        System.out.println(v.size());

        XlsUtil util=new XlsUtil();
        File aFile=new File("D:/test/文学类.xls");
        System.out.println(util.checkXls(aFile));

       // 测试 将问题导入题库
        QLibrary ql = new QLibrary();
        ql.setId(3L);
        boolean b1 = new QuestionService().addQuestionToQL(v,ql);
        if(b1){

            //若导入成功，测试获取一个题库的所有题目
            v = new QuestionService().getAllQuestionOfQL(ql);
            System.out.println(v.size());
        }
    }
}
