package com.example.demo.vo;


import com.example.demo.bean.TbItemParamValue;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable{
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String barcode;
    private String image;
    private Long cId;
    private String des;
    private List<TbItemParamValue> params;

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

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<TbItemParamValue> getParams() {
        return params;
    }

    public void setParams(List<TbItemParamValue> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price='" + price + '\'' +
                ", num='" + num + '\'' +
                ", barcode='" + barcode + '\'' +
                ", image='" + image + '\'' +
                ", cId=" + cId +
                ", des='" + des + '\'' +
                ", params=" + params +
                '}';
    }
}
