package com.foot.tourpal.base.tool;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 卡券相关操作
 *
 * @author ZhangPu
 * @version 1.0.0
 */
public class HttpRequestSign {

    private final static String CHECK = "Amo29@SWp~0l,awpmx%Fa@a`";

    /**
     * 参数排序
     */
    public static String getRequestSign(Map<String, String> map) {
        if (map == null)
            return "";
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                map.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, String> map1 = (Map.Entry<String, String>) infoIds
                    .get(i);

            // value是数组 不加入签名
            Pattern p = Pattern.compile("^.*\\[[.*]+\\]$");
            Matcher m = p.matcher(map1.getKey());
            if (m.matches() == false) {
                builder.append(map1.getKey() + map1.getValue());
            }

        }
        builder.append(CHECK);
        return string2MD5(builder.toString());

    }

    /***
     * MD5加码 生成32位md5码
     */
      static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
