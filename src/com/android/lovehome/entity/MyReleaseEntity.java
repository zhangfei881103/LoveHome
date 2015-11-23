package com.android.lovehome.entity;


public class MyReleaseEntity {
    private String id;
    private String imgUrl;
    private String name;
    private String tagV;
    private String tagWai;
    private String price;
    private String address;
    private String type;//区分类型：酒店
    private String time;
    private String typeMy;//区分发布 收藏 草稿

    public MyReleaseEntity(String imgUrl, String name, String price) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagV() {
        return tagV;
    }

    public void setTagV(String tagV) {
        this.tagV = tagV;
    }

    public String getTagWai() {
        return tagWai;
    }

    public void setTagWai(String tagWai) {
        this.tagWai = tagWai;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTypeMy() {
        return typeMy;
    }

    public void setTypeMy(String typeMy) {
        this.typeMy = typeMy;
    }
}
