package com.wu.md.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class NavInfo implements MultiItemEntity {

    public static final int MSG_TYPE_VIP = 1;
    public static final int MSG_TYPE_CENTER = 2;

    public int type;
    public String title;
    public int icon;

    public NavInfo(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public NavInfo(int type, String title, int icon) {
        this.type = type;
        this.title = title;
        this.icon = icon;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
