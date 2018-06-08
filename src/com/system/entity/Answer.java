package com.system.entity;

import java.io.Serializable;

public class Answer implements Serializable{
    private long answerID = -1L;
    private int answerScore = 0;
    private String answerTime = "2018-05-20 09:00:00"; // 初始化
    private int answerContent = 0;
    private boolean isChecked = false;

    public long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(long answerInfoID) {
        this.answerID = answerInfoID;
    }

    public int getAnswerScore() {
        return answerScore;
    }

    public void setAnswerScore(int answerScore) {
        this.answerScore = answerScore;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public int getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(int answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

}
