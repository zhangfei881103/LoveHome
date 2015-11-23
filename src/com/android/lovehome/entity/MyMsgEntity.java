package com.android.lovehome.entity;


public class MyMsgEntity {
    private String imgurl;
    private String title;
    private String content;
    private String time;
    private String num;

    public MyMsgEntity(String imgurl, String title, String content, String time, String num) {
        this.imgurl = imgurl;
        this.title = title;
        this.content = content;
        this.time = time;
        this.num = num;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
