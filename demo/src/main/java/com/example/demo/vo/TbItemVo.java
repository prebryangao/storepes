package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class TbItemVo implements Serializable{
    private Long id;
    private String title;
    private String sellPoint;
    @JsonIgnore
    private Long price;
    private Integer num;
    private String barcode;
    @JsonIgnore
    private String image;
    private String tbItemCatName;//分类名称
    private Byte status;
    private Date created;
    private Date updated;
    //自己人工定义了一个get方法 他的名字itemPrice
    public String getItemPrice(){
        String integer = getPrice()/100+"";
        String decimal = getPrice()%100==0?"00":getPrice()%100+"";
        return integer+"."+decimal;
    }
    public String getItemImage(){
        String image = getImage();
        String[] split = image.split(",");
        //获取第一张图片
        return split[0];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTbItemCatName() {
        return tbItemCatName;
    }

    public void setTbItemCatName(String tbItemCatName) {
        this.tbItemCatName = tbItemCatName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "TbItemVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price='" + price + '\'' +
                ", num=" + num +
                ", barcode='" + barcode + '\'' +
                ", image='" + image + '\'' +
                ", tbItemCatName='" + tbItemCatName + '\'' +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
