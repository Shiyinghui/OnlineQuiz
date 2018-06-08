package com.system.entity;

import java.io.Serializable;

public class Announce implements Serializable{

    private long id=-1L;
    private String title="未知";
    private String content="未知";
    private String time="2018-04-01 00:00:00";
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
