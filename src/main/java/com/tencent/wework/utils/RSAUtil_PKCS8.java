package com.tencent.wework.utils;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAUtil_PKCS8 {
    private static final String privKeyPEM = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCrIgFQREm4KvXD\n" +
            "k1EVsrkyZjV5UPdQgZtnFN0oLYPo5OXgebDKRiNrIYmVNNhrDELp7/CsAv68z74t\n" +
            "iu7DS63TCYyGSiZZhJScHWBVcl4i95xTwoJ+JzZslfXL+25ctmoLENPKCF0iACtG\n" +
            "ByiVtUDAGbHRyW4jIN+fo6I59s55OODUoYd6kLcpZ5DwM8qEizIwJ5Lq1yYEJyLf\n" +
            "u/Ven2DB4QD35VeAM56LuvGc5TdQIJYgIW1eViDSbu4BkeKmjrjZ+m6BV9ltJjXF\n" +
            "Mr8WFgQynPtZNbRTmq3/qrV1wYvsLjzz8jkI+Xtr5fWOUT4y0RBRcJYdTDrfFBvM\n" +
            "mESo04JHAgMBAAECggEADZK+4+zmo/LUV1TB46SvR64Po7WmR/5fowdmmx8xDTHK\n" +
            "gcllGkYB3Sdl0C3G1Ia+b+uV/S6nobYgcWBJm7fO66QgPy52aDc5ykASOfS+PqvA\n" +
            "/Gu7CeJ2UJp8SleA412cf5AwHCAZsu6sf/tlXqpnnlTrY6ZEVAjxc6n43fwTWE3n\n" +
            "YaU7M18D9VRVqSmB3EPI1XtPr3xNHXQm0mmsFCa+Idq6zcDBhf3T89DClXYzuHOx\n" +
            "s8g3bByF6PauhRr/+cr3Vg4PmjvxbH6CqLKi0h9vB5SZvGEZDD71TYbSgEgnVBxG\n" +
            "ulcFHd3IxetvVj3yNkqgkmbp6O1fOqf/NDOSqzCZoQKBgQDVg7bdMBQwS7wZOBrI\n" +
            "p/DIVyKjzyBM5QRXyJ3f7XVYBhGDTX38N31PY3jngMQRYPFDpSSbdiyIKWLj8zZh\n" +
            "jZCGkSlRuiYIptg5xrhiVowTOCo00zcI07nH0gKRHisi+8ue4xEnUNvEg9MO0vQG\n" +
            "HTAO75iPI/6Az1i5lUcNizv1LwKBgQDNL2WNAciAvfBFHX6buqNr2paOo1k+fVZk\n" +
            "uLsRUFLMhWV5HGOmNI082pr2/XUcJpIC0fdxENhuhyJTWaJ4GFsGmVIQpeyWV8qg\n" +
            "uV25ATm0vsah1Vgit1VZATo3GeUIGd95MmGjQfiMd8Y09NMZvCZt+fPJ6XsHYdHy\n" +
            "aNL+o0quaQKBgHOvgwQDSd0FG+x6JqDzxZV3CNDCqBihuaGqlEfBDGReHV0MPqfK\n" +
            "lP3PwrGEZjeYVAC32MbW17gK3Ibvv1ZJ8rXGryGV/4eOhvd/AWZeyFSdiNjIXDhP\n" +
            "J4jp8/QW6X5x+VikZSXtPLYUSuXsTMWHSQO+s+VStlGqhsM+65meCCWHAoGBALaR\n" +
            "arFZmdb7qjNFOCdGnSxVVctbCwhwK5P5TvHr35B9TTnDCeOUNOuls2b7RN2kcBy0\n" +
            "SJBBJeLKuL8jLU3glfW8LGoFyQpI2VHDPeJkdb0eVs0ecIqLSBSm4+6tSr23j8Fe\n" +
            "E8RY6dfKpwDtqnvmjuciFUpeRgrefhlMLhlGoCepAoGAWkGQQfwtwT8fcCh/Ib6p\n" +
            "+ULTBG3Nn0zvWpLZdaBC5IPYrb67vOAgfN/Ennv1SIq0zYlz+jKfGECr4If3qZon\n" +
            "p4+k+i6M/HYvXm+WMaAxM/2xDVGmwmOu+LVIlXz2eIvsT5DFrOSOExblRy5Y3Af+\n" +
            "Jx7Jw9zBMOBuiroqAHwyf90=\n" +
            "-----END PRIVATE KEY-----\n";

    // 用此方法先获取秘钥
    public static String getPrivateKey(String encrypt_random_key) throws Exception {

        String privKeyPEMnew = privKeyPEM.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");

        byte[] decoded = Base64.getDecoder().decode(privKeyPEMnew);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(encrypt_random_key);

        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);

        return new String(cipher.doFinal(inputByte));
    }
}
