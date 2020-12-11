package com.example.demo.utils;

import java.io.Serializable;

public class PicData implements Serializable{
    private String src;
    private String title;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PicData{" +
                "src='" + src + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
