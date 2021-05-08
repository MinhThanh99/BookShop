package com.minhthanh.bookshop.home.img_slider;

public class Slide {

    private int resourceID;//dùng cho ảnh thuần
    private String thumb; //link ảnh
    private String toLink; // đường dẫn quảng cáo

    public Slide(int resourceID, String thumb, String toLink) {
        this.resourceID = resourceID;
        this.thumb = thumb;
        this.toLink = toLink;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getToLink() {
        return toLink;
    }

    public void setToLink(String toLink) {
        this.toLink = toLink;
    }
}
