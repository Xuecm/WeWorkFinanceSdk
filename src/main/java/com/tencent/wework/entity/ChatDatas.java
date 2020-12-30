package com.tencent.wework.entity;

import java.io.Serializable;
import java.util.List;

public class ChatDatas implements Serializable {
    private static final long serialVersionUID = 1700586620843625231L;
    private String errcode;
    private String errmsg;
    private List<Qychat> chatdata;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<Qychat> getChatdata() {
        return chatdata;
    }

    public void setChatdata(List<Qychat> chatdata) {
        this.chatdata = chatdata;
    }
}
