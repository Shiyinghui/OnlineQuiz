package test;

import com.system.util.TimeUtil;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TestTime {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // 时间测试
        String time="2018-05-11 13:00:00";
        TimeUtil ti = new TimeUtil();
        ti.parseStringToDate(time);

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime=format.format(date);//time就是当前时间
        System.out.println(curTime);

        System.out.println(new TimeUtil().getTime());
    }
}
