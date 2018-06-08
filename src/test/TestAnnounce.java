package test;

import com.system.service.AnnounceService;
import com.system.entity.Announce;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class TestAnnounce {

    public static void main(String[] args){

        // 查看系统所有公告
        List<Announce> list = new ArrayList<>();
        list = new AnnounceService().getAllAnnounce();
        Iterator<Announce> it = list.iterator();
        while(it.hasNext()){
            Announce announce = new Announce();
            announce = it.next();
            System.out.println(announce.getId());
            System.out.println(announce.getTitle());
            System.out.println(announce.getContent());
            System.out.println(announce.getTime());
        }

        // 新发布一条公告 已测试
        /*Announce announce = new Announce();
        announce.setTitle("知识问答网站");
        announce.setContent("快来答题吧");
        announce.setTime("2018-5-21 18:36:20");
        new AnnounceService().addAnnounce(announce);*/

        // 删除一条公告，已测试
        /*Announce a = new Announce();
        a.setId(6l);
        new AnnounceService().deleteAnnounce(a);
        */
    }
}
