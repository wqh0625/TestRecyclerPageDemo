package com.wqh.livedatabug.list.bean;

/**
 * 作者: Wang on 2019/11/17 15:08
 * 寄语：加油！相信自己可以！！！
 */


public class AnimationBean {
    private int count;
    private String name;
    private int isVote;

    public void setIsVote(int isVote) {
        this.isVote = isVote;
    }

    public int getIsVote() {
        return isVote;
    }

    public AnimationBean(int count, String name,int isVote) {
        this.count = count;
        this.name = name;
        this.isVote = isVote;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }


}
