package com.ball.wx.util;


import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

public class AESUtil {

    public static Integer getTodayRunData(String data, String session_key, String iv) {
        try {
            //  加密的数据
            byte[] dataByte = Base64.decode(data);
            //  加密的密钥
            byte[] keyByte = Base64.decode(session_key);
            //  加密的偏移量
            byte[] ivByte = Base64.decode(iv);
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(result);
                String[] strings = jsonObject.get("stepInfoList").toString().split("\\}");
                Integer[] integers = new Integer[strings.length - 1];
                for (int i = 0; i < strings.length - 1; i++) {
                    integers[i] = Integer.parseInt(strings[i].split("\\:")[1].split(",")[0]);
                }
                return integers[integers.length - 1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}