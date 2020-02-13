package com.yuan.library.bottomdialog;

import android.graphics.drawable.Drawable;

/**
 * yuan
 * 2020/2/12
 **/
public class Item {
    private int id;
    private String title;
    private Drawable icon;

    public Item() {
        super();
    }

    Item(int id, String title, Drawable icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
