package com.wqh.livedatabug.list.bean;

/**
 * 作者: Wang on 2019/11/17 13:28
 * 寄语：加油！相信自己可以！！！
 */


public class NewslistBean {
    private String title;
    private String desc;

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public NewslistBean(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }
}
