package com.system.entity;
import java.util.*;

public class TypeBank {

    private String type1 = "生活常识类";
    private String type2 = "文学类";
    private String type3 = "娱乐类";
    private String type4 = "天文地理类";
    private String type5 = "体育文化类";

    public List<String> getBankType() {
        List<String> banklists = new ArrayList<String>();
        banklists.add(type1);
        banklists.add(type2);
        banklists.add(type3);
        banklists.add(type4);
        banklists.add(type5);
        return banklists;
    }
}