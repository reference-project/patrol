package com.slk.patrol.framework.util;

import java.util.Random;

public class JSSecretUtil {




    public static void main(String[] args) {
        String code = (Math.random() * 26 + 97)+"";
        System.out.println(code);
        String s = compileStr(code);
        System.out.println(s);
        System.out.println(uncompileStr(s));
    }




    /**
     *
     * 获取c在Unicode位置
     * Create by song-xl on 2018-04-24 03:14:26
     */
    private static String getUnicode(char c){
        String returnUniCode=null;
        returnUniCode=String.valueOf((int)c);
        return returnUniCode;
    }

    
    /**
     *
     * 对字符串进行加密 字符串转unicode
     * Create by song-xl on 2018-04-24 03:08:04
     */
    public static String compileStr(String code){
        int unicode = Integer.valueOf(getUnicode(code.charAt(0)))+code.length();
        String c = String.valueOf((char) unicode);
        for (int i = 1; i < code.length(); i++) {
            int i1 = Integer.valueOf(getUnicode(code.charAt(i))) + Integer.valueOf(getUnicode(code.charAt(i - 1)));
            c += String.valueOf((char) i1);
        }
        return escape(c);
    }



    /**
     *
     * 对字符串进行解密 unicode转字符串
     * Create by song-xl on 2018-04-24 03:08:04
     */
    public static String uncompileStr(String code){
        code = unescape(code);
        int unicode = Integer.valueOf(getUnicode(code.charAt(0)))-code.length();
        String c = String.valueOf((char) unicode);
        for (int i = 1; i < code.length(); i++) {
            int i1 = Integer.valueOf(getUnicode(code.charAt(i))) - Integer.valueOf(getUnicode(c.charAt(i - 1)));
            c += String.valueOf((char) i1);
        }
        return c;
    }




    /**
     *
     * 同JS中的escape--加密
     * Create by song-xl on 2018-04-24 03:05:12
     */
    private static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }



    /**
     *
     * 同JS中的unescape--解密
     * Create by song-xl on 2018-04-24 03:05:42
     */
    private static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }




}
