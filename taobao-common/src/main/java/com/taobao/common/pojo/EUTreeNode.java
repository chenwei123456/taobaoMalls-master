package com.taobao.common.pojo;

/**
 * Created by chenwei on 2017/10/18.
 */

//树形控制菜单
public class EUTreeNode {
    private long id;
    private String text;
    private String state;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public EUTreeNode(long id, String text, String state) {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
    }
    public EUTreeNode() {
        super();
    }
}
