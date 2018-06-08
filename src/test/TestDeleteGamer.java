package test;

import com.system.service.deleteGService;
import com.system.entity.Gamer;

public class TestDeleteGamer{

     public static void main(String[] args){
         Gamer g = new Gamer();
         g.setEmail("g5@test.com");
         boolean b = new deleteGService().delete(g);
         if(b)
             System.out.println("OK");

     }

}
