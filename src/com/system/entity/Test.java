package com.system.entity;

import java.io.Serializable;
public class Test implements Serializable{
    private String testTime = "2018-05-30 20:00:00";
    private long testID = -1L;  // 测试ID
    private int testScore = 0;
    public long getTestID() {
        return testID;
    }

    public void setTestID(long testID) {
        this.testID = testID;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public int getTestScore(){ return testScore;}

    public void setTestScore(int score){ this.testScore = score; }

}
