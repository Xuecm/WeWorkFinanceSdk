package com.tencent.wework.utils;

import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;

public class RSAUtil_PKCS1 {

    private static final String privKeyPEM = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAujDIejA0rJQanJNNHzB9+cHKsYaoJ0lrcWC5ErunJf77RiF2\n" +
            "23QiP0Ud81A8z2+L4hDTfUC/rrVxq7q6ZUu5H7zhdBaUnYM4EDNvsn7G5stSVb2U\n" +
            "vlb9SsPI/iAM8NWSp1SMx7Q24hQI7QWpSg+Ek6UKA4ccY7d8ilW4cfUyKIYVxkA3\n" +
            "PlLnDIyh+62pPNlZfwMIABjIbuQTiVa5BGMTl217QkDi7fjy3tL30Eu9/rSv68cG\n" +
            "gWgX4oXhCrek5GKG6kK+1ZV6lDy/Sbnu5S7Ft0H7f9+dxj4TWzZsVHTWumHI0PRI\n" +
            "ZVvU+rCYJQSMPJS/TgKe5WI727Nh8Ju0GVTdYQIDAQABAoIBAFMDlR8Fhza7AfJ1\n" +
            "qpkRYdmNkfX899QsY/81BM9ZY3kJarcmDHxkFzDmfCgTykkijtfIcMMTr2MR+xB6\n" +
            "PMkVLgD8kfjUaIaiDskKZe7Ln5sfZCJeWIdU+bHwARls6NgoIS8B/5K6ww2hqSkk\n" +
            "sQoYSSAbIY/ZGctL7/ctqQMGM8rk2acWmmind8mJyYgCxkKTRYj/Bi9petRVokl9\n" +
            "ydHbWOVm2m9v8epXs7tI5pr9atfwVYgBT6yloPHDuoziRXN3nsfxYgwG1V4Hx6er\n" +
            "54oFH6fBX9g8kVzrex1EKeMy1Vlx3heFZyWLp8eTB/Gs9dQxvguweEyglP24BD6y\n" +
            "xHQ8tMECgYEA9UqYMQV5Lm66Yr04mrX04QI6mkfTcaPc0A91MVkv2hdOAI9AQ9mp\n" +
            "MATcHbKdCw4jG2NDbdX05BtOCc6nBGmTw7x0GJKZSiTq0D/Yx5qN+PlwmfWNqo/L\n" +
            "Bicbn4xhfC4V5GFEvu7uZ/lmbyyPBSeor2jivTIL45GcCJSqI5Kk4iUCgYEAwlGr\n" +
            "udTjG/V0KSZy+B8luPeJ85GDu895lz12r3KF3tV1X/Cx5k4EAwbZyJg7mMje1yZd\n" +
            "VKGXUbYlQ7EkMj/jdDo/H/V1gEI1Lrgf49lbyFFziQ5vKcOQ8bjcCuvHlSzOnMIU\n" +
            "aBwF7YUaOTCgowCHrM/LooF2FL0QN8NDb5fQY40CgYEA8G7o1QxHZzMUMVrGlCeM\n" +
            "281igf2rjbWP6MVuGywP0peLVtMTrC47P51T4SxF8SMvWFYpLw1M2RbGnKhaX+HY\n" +
            "/EDKD5gK93lOTUh4BddCGGzwaYkfDrOZzvMgiXiBycYmoVCSoRD00SfOt2SW3/eq\n" +
            "yh1mOwtGSg9dQa/vQaUpR50CgYBJH561wYztOoLJ8XQWNvJlFpHNHJcsF7kz1qdR\n" +
            "ApGp9z3CKrtnZThRQ0S1HcRfjEd/Qyqf7GcbEqYXQRPT7QLU/A5BIodjCock25j0\n" +
            "jxjg2vXVP59JYhSrQdtcKLnkQwZFKyuuuczpQstJdikc7dggidmhf4JF75eoxEwe\n" +
            "NEzxCQKBgQDlsfxDLTIark3fStGRdfJlP14trnylbky8qMp+CKpPBsgB83bnzc2R\n" +
            "T95T5Lzyuhqg9Yg/h7JlPgJY9wAuJgx6Ee+QU8f/+B/LowJc6pTiydjsOhkhoUuM\n" +
            "OIw+2X0X1aM5lIZ8CCTgDG8df9oWTwAkPXKW3FRKq5f3xKvInNtc4Q==\n" +
            "-----END RSA PRIVATE KEY-----";

    // 用此方法先获取秘钥
    public static String getPrivateKey(String encrypt_random_key) throws Exception {

        String privKeyPEMnew = privKeyPEM.replaceAll("\\n", "").replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "");
        byte[] bytes = java.util.Base64.getDecoder().decode(privKeyPEMnew);

        DerInputStream derReader = new DerInputStream(bytes);
        DerValue[] seq = derReader.getSequence(0);
        BigInteger modulus = seq[1].getBigInteger();
        BigInteger publicExp = seq[2].getBigInteger();
        BigInteger privateExp = seq[3].getBigInteger();
        BigInteger prime1 = seq[4].getBigInteger();
        BigInteger prime2 = seq[5].getBigInteger();
        BigInteger exp1 = seq[6].getBigInteger();
        BigInteger exp2 = seq[7].getBigInteger();
        BigInteger crtCoef = seq[8].getBigInteger();

        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] inputByte = Base64.getMimeDecoder().decode(encrypt_random_key);
        String s = new String(cipher.doFinal(inputByte));

        return s;
    }
}
