package com.tencent.wework.entity;

import java.io.Serializable;

public class Qychat implements Serializable {
    private static final long serialVersionUID = 1L;
    private String seq;
    private String msgid;
    private String publickey_ver;
    /**加密RSA秘钥*/
    private String encrypt_random_key;
    /**加密消息*/
    private String encrypt_chat_msg;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getPublickey_ver() {
        return publickey_ver;
    }

    public void setPublickey_ver(String publickey_ver) {
        this.publickey_ver = publickey_ver;
    }

    public String getEncrypt_random_key() {
        return encrypt_random_key;
    }

    public void setEncrypt_random_key(String encrypt_random_key) {
        this.encrypt_random_key = encrypt_random_key;
    }

    public String getEncrypt_chat_msg() {
        return encrypt_chat_msg;
    }

    public void setEncrypt_chat_msg(String encrypt_chat_msg) {
        this.encrypt_chat_msg = encrypt_chat_msg;
    }
}
