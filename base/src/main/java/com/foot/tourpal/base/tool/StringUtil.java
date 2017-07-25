package com.foot.tourpal.base.tool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import timber.log.Timber;

/**
 * Created by ZhangPu on 2017/7/4.
 */

public class StringUtil  {
    public static float measureTextLength(float size, String content) {
        Paint paint = new Paint();
        paint.setTextSize(size);
        return paint.measureText(content);
    }

    // 隐藏手机中间4位号码
    public static String hideMidPhoneNum(String mobile) {
        if (null == mobile || mobile.isEmpty()) {
            return "";
        }
        String hideNum = mobile.trim().substring(3, 7);
        return mobile.replace(hideNum, "****");
    }

    // 去除高级备注中的<x> </x>
    public static String removeTags(String remark) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < remark.length(); i++) {
            char c = remark.charAt(i);
            if (c == '<') {
                char n = remark.charAt(i + 1);
                if (n == '/') {
                    i += 3;
                } else {
                    i += 2;
                }
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    // 判断邮箱是否合法
    public boolean checkEmail(String str) {
        if (!TextUtils.isEmpty(str)) {
            String regex = "[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9]+(\\.(com))";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            if (matcher.matches()) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public static String remove86(String phone) {
        if (phone.startsWith("+86")) {
            phone = phone.substring("+86".length(), phone.length());
        } else if (phone.startsWith("86")) {
            phone = phone.substring("86".length(), phone.length());
        }
        return phone;
    }

    // 判断手机号是否合法
    public static boolean checkPhone(String str) {
        str = str.trim();
        if (!TextUtils.isEmpty(str)) {
            String regex = "^0?(13[0-9]|15[012356789]|18[012356789]|17[0-9]|14[57])[0-9]{8}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            if (matcher.matches()) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    // 判断是否为字符串长度
    public int checkLength(String str, int min, int max) {
        if (!TextUtils.isEmpty(str)) {
            if (str.length() > max || str.length() < min) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String fuwuNumberToShowNum(int num) {
        if (String.valueOf(num).length() <= 5) {
            return String.valueOf(num);
        } else {
            float numF = num / 10000.0f;
            String returnStr = String.valueOf(numF);
            return returnStr.substring(0, returnStr.indexOf(".") + 2) + "万";
        }
    }

    public String ReplaceXing(String num) {
        if (num.startsWith("+86")) {
            num = num.substring("+86".length(), num.length());
        }
        StringBuffer buffer = new StringBuffer();
        for (int index = 0; index < num.length(); index++) {
            if (index >= 3 && index <= 6) {
                buffer.append("*");
            } else {
                buffer.append(num.charAt(index));
            }
        }
        return buffer.toString();
    }

    // 解析Struts返回的结果
    public String parseStr(String str) {
        return str.replaceAll("[\\p{Punct}\\p{Space}]+", "");
    }

    /**
     * 根据经纬度计算距离
     *
     * @param lat1
     * @param lng1
     * @param lat_local
     * @param lng_local
     * @return
     */
    public static double GetDistance(double lat1, double lng1,
                                     double lat_local, double lng_local) {
        double M_PI = 3.14159265358979323846264338327950288;
        double dd = M_PI / 180;
        double x1 = lat1 * dd, x2 = lat_local * dd;
        double y1 = lng1 * dd, y2 = lng_local * dd;
        double R = 6371004;
        double distance = (2 * R * Math.asin(Math.sqrt(2 - 2 * Math.cos(x1)
                * Math.cos(x2) * Math.cos(y1 - y2) - 2 * Math.sin(x1)
                * Math.sin(x2)) / 2));
        return distance;
    }

    public static int convertDipOrPx(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    @SuppressWarnings("deprecation")
    public static boolean isFullScreen(Activity mContext, ListView lv,
                                       Adapter adapter) {
        int screenHeight = mContext.getWindowManager().getDefaultDisplay()
                .getHeight()
                - convertDipOrPx(mContext, 145);
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, lv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        int count = adapter.getCount();
        int height = totalHeight + (lv.getDividerHeight() * (count - 1));

        if (height < screenHeight) {
            return false;
        }
        return true;
    }


    public static String calculateDis(double distance) {
        int distance_ = Integer
                .valueOf(String.valueOf(distance).split("\\.")[0]);
        String str = null;
        if (distance_ < 100) {
            str = distance_ + "m";
        } else if (distance_ >= 100 && distance_ < 1000) {
            str = distance_ / 10 * 10 + "m";
        } else if (distance_ >= 1000 && distance_ < 10000) {
            String a = distance_ / 100 + "";
            str = a.substring(0, 1) + "." + a.substring(1, 2) + "Km";
        } else if (distance_ >= 10000 && distance_ < 100000) {
            String a = distance_ / 100 + "";
            str = a.substring(0, 2) + "." + a.substring(2, 3) + "Km";
        } else {
            str = "> 100km";
        }
        return str;

    }

    public static String calculateTime(int time) {
        String str = "";
        if (time < 60) {
            str = time + "秒";
            return str;
        } else if (time < 60 * 60) {
            time = time / 60;
            str = time + "分钟";
            return str;
        } else {
            int minuteTime = time / 60;

            int hours = minuteTime / 60;
            int minute = minuteTime % 60;
            if (hours == 0) {
                str = minute + "分钟";
            } else {
                str = hours + "小时" + minute + "分钟";
            }
            return str;
        }
    }

    /**
     * 得到格式化json数据 退格用\t 换行用\r
     */
    public static String formatJson(String jsonStr) {
        int level = 0;
        jsonStr = "\n" + jsonStr;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0
                    && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();
    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("    ");
        }
        return levelStr.toString();
    }

    /**
     * 判断是否包含特殊字符
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean checkSpecialChar(String str)
            throws PatternSyntaxException {
        String regEx = ".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]+.*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String encodePhoneNum(String phone) {
        if (checkPhone(phone)) {
            String part1 = phone.substring(0, 3);
            String part2 = phone.substring(7, phone.length());
            return part1 + "****" + part2;
        }
        return phone;

    }

    public boolean hasNum(Character str) {
        if (str.equals('0') || str.equals('1') || str.equals('2')
                || str.equals('3') || str.equals('4') || str.equals('5')
                || str.equals('6') || str.equals('7') || str.equals('8')
                || str.equals('9')) {
            return true;
        } else {
            return false;
        }
    }

    public static String padLeft(String txt, int len) {
        int length = 0;
        StringBuilder ret = null;
        try {
            length = txt.getBytes("GBK").length;
            ret = new StringBuilder(txt);
            if (len > length) {
                int pad = len - length;
                String padchars = String.format("%1$" + pad + "s", "");
                ret.insert(0, padchars);
            }
            return (ret.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return txt;
    }

    public static String padRight(String txt, int len) {
        int length = 0;
        StringBuilder ret = null;
        try {
            length = txt.getBytes("GBK").length;
            ret = new StringBuilder(txt);
            if (len > length) {
                int pad = len - length;
                String padchars = String.format("%1$" + pad + "s", "");
                ret.append(padchars);
            }
            return (ret.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return txt;
    }

    public static String[] formatLn(int size, String orginStr)
            throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> list = Arrays.asList(orginStr.replaceAll("\\s{2,}", " ")
                .split(" "));
        int newSize = 0;
        for (String str : list) {
            if (str.getBytes("GBK").length > size) {
                if (stringBuffer.length() != 0) {
                    stringBuffer.append("\n");
                }
//				stringBuffer.append("\n").append(str).append("\n");
                char[] Characterlist = str.toCharArray();
                int newChatSize = 0;
                for (char c : Characterlist) {
                    newChatSize += Character.toString(c).getBytes("GBK").length;
                    if (newChatSize <= size) {
                        stringBuffer.append(c);
                    } else {
                        stringBuffer.append("\n").append(c);
                        newChatSize = Character.toString(c).getBytes("GBK").length;
                    }
                }
                stringBuffer.append(" ");
                newSize = newChatSize + 1;
                continue;
            }
            newSize += str.toString().getBytes("GBK").length;
            if (newSize <= size) {
                if (list.indexOf(str) == list.size() - 1) {
                    stringBuffer.append(str);
                } else {
                    stringBuffer.append(str).append(" ");
                    newSize++;
                }
            } else {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                stringBuffer.append("\n").append(str).append(" ");
                newSize = str.toString().getBytes("GBK").length + 1;
            }
        }
        return stringBuffer.toString().split("\n");
    }

    public static final String CHARSET_UTF8 = "utf-8";
    public static final String CHARSET_GB2312 = "GB2312";

    public static String bytes2String(byte[] inBytes) {
        return bytes2String(inBytes, CHARSET_GB2312);
    }

    public static String bytes2String(byte[] inBytes, String charset) {
        String output = null;
        if (inBytes != null && inBytes.length > 0) {
            try {
                output = new String(inBytes, charset);
            } catch (UnsupportedEncodingException e) {
                output = null;
            }
        }
        return output;
    }

    public static byte[] String2bytes(String inString) {
        byte[] output;
        try {
            output = inString.getBytes(CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
            output = null;
        }
        return output;
    }

    /**
     * String转为int，如果异常， 则返回-1
     *
     * @param str String|待转换为int的字符串
     * @return int|转换后的value
     */
    public static int toInt(String str) {
        return toInt(str, -1);
    }

    /**
     * String转为int，如果异常， 则返回{@code #defaultValue}
     *
     * @param str          String|待转换为int的字符串
     * @param defaultValue int|如果出现异常，则返回该字段
     * @return int|转换后应该返回value
     */
    public static int toInt(String str, int defaultValue) {
        int value = defaultValue;
        if (!TextUtils.isEmpty(str)) {
            try {
                value = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                value = defaultValue;
                Timber.e("originvalue=" + str, e);
            }
        }
        return value;
    }

    /**
     * String转为float
     *
     * @param str          String
     * @param defaultValue float
     * @return float
     */
    public static float toFloat(String str, float defaultValue) {
        float value = defaultValue;
        if (!TextUtils.isEmpty(str)) {
            try {
                value = Float.parseFloat(str);
            } catch (NumberFormatException e) {
                value = defaultValue;
                Timber.e("originvalue=" + str, e);
            }
        }
        return value;
    }

    /**
     * String转为long
     *
     * @param str          String
     * @param defaultValue long
     * @return long
     */
    public static long toLong(String str, long defaultValue) {
        long value = defaultValue;
        if (!TextUtils.isEmpty(str)) {
            try {
                value = Long.parseLong(str);
            } catch (NumberFormatException e) {
                value = defaultValue;
                Timber.e("originvalue=" + str, e);
            }
        }
        return value;
    }

    public static String subString(String content, int length) {
        return content.length() < length ? content : content.substring(0, length);
    }

    /**
     * 获取异常信息
     *
     * @param e Exception
     * @return String
     */
    public static String getExceptionInfo(Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

    /**
     * 从 Unicode 形式的字符串转换成对应的编码的特殊字符串。 如 "\u9EC4" to "黄".
     * Converts encoded \\uxxxx to unicode chars
     * and changes special saved chars to their original forms
     *
     * @param in  Unicode编码的字符数组。
     * @param off 转换的起始偏移量。
     * @param len 转换的字符长度。
     * @return 完成转换，返回编码前的特殊字符串。
     */
    public static String fromEncodedUnicode(char[] in, int off, int len) {
        char aChar;
        char[] out = new char[len]; // 只短不长
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = (char) aChar;
            }
        }
        return new String(out, 0, outLen);
    }


    /**
     * info是空或者0
     *
     * @param info String
     * @return boolean | true:为空或者0;false,不为0的字符串
     */
    public static boolean emptyInt(String info) {
        return TextUtils.isEmpty(info) || TextUtils.equals("0", info);
    }

    public static boolean isMsgWeb(String msg) {
        return msg.contains("<!DOCTYPE html>") || msg.contains("<div") || msg.contains("<br") || msg.contains("<b");
    }

    public static String removeStart0(String source) {
        return source.replaceAll("^0+(?!$)", "");
    }

    public static String add0ToLeft(int length, String source) {
        StringBuilder builder = new StringBuilder(source);
        builder.reverse();
        for (; builder.length() < length; ) {
            builder.append("0");
        }
        return builder.reverse().toString();
    }

    //移除小数后面的0
    public static String removeEnd0(String source) {
        if (source != null && source.indexOf(".") > 0) {
            source = source.replaceAll("0+?$", "");//去掉多余的0
            source = source.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return source;
    }

    /**
     * 删除所有空格
     *
     * @param s
     * @return
     */
    public static String deleteWhitespace(String s) {
        if (!TextUtils.isEmpty(s))
            return s.replace(" ", "");
        return s;
    }

}
