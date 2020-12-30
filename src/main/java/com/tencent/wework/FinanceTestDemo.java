package com.tencent.wework;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wework.entity.ChatDatas;
import com.tencent.wework.entity.Qychat;
import com.tencent.wework.utils.RSAUtil_PKCS1;
import com.tencent.wework.utils.RSAUtil_PKCS8;

import java.util.List;

public class FinanceTestDemo {
    /**
     * 打开企业微信管理端-我的企业-最下面
     * */
    private static final String CORP_ID = "xxx";
    /**
     * 打开企业微信管理端-管理工具-会话内容存档-secret
     * */
    private static final String SECRET = "xxx";

    public static void main(String[] args){
        long sdk = Finance.NewSdk();
        System.out.println(Finance.Init(sdk, CORP_ID, SECRET));
        long slice = Finance.NewSlice();
        //参考API文档参数说明，根据需要进行修改
        Finance.GetChatData(sdk, 0, 10, "", "", 10, slice);
        System.out.println("getChatData :" + Finance.GetContentFromSlice(slice));
        JSONObject chatResultJson = JSONObject.parseObject(Finance.GetContentFromSlice(slice));
        ChatDatas cdata = JSON.toJavaObject(chatResultJson, ChatDatas.class);
        List<Qychat> list = cdata.getChatdata();
        for (Qychat qychat : list) {
            String encrypt_chat_msg = qychat.getEncrypt_chat_msg();
            String privateKey = null;
            try {
                //注意生成公钥时使用的是 PKCS8还是PKCS1
                privateKey = RSAUtil_PKCS1.getPrivateKey(qychat.getEncrypt_random_key());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(privateKey);
            // 将获取到的数据进行解密操作
            long msg = Finance.NewSlice();
            Finance.DecryptData(sdk, privateKey, encrypt_chat_msg, msg);
            String content = Finance.GetContentFromSlice(msg);
            System.out.println("------------获得解密后的内容-----------"+content);
        }
        Finance.FreeSlice(slice);
        Finance.DestroySdk(sdk);
    }
}
