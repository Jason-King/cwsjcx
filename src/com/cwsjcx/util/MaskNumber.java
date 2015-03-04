package com.cwsjcx.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HUANG on 14/8/13.
 * 用于隐藏用户电话号码以及身份证号等敏感信息
 */
public class MaskNumber {
    /**
     * Mask iD card number.
     *
     * @param s the ID card number
     * @return the the ID card number after masking
     */
    public static String maskIDCardNumber(String s) {

        return s;
    }

    /**
     * Mask bank card number.
     *
     * @param s the bank card number
     * @return the bank card number after masking
     */
    public static String maskBankCardNumber(String s) {

        int l = s.length();

        int i = l - 4;//截取最后四个字符；

        return "尾号" + s.substring(i, l);
    }

    /**
     * Mask phone number.
     *
     * @param s the phone number
     * @return the phone number after masking
     */
    public static String maskPhoneNumber(String s) {
    	String str = "";
    	if(null!=s&&!"".equals(s)){
    		Pattern p = Pattern.compile("^([0-9]{3})([0-9]{4})([0-9]{4})$");
            Matcher m = p.matcher(s);
            while (m.find()) {
                str = m.group(1) + "****" + m.group(3);//替换中间四位
            }
    	}
        
        return str;
    }

    public static String subStringPhoneNumber(String s) {
        return s.substring(s.length() - 4, s.length());
    }
    
    public static String maskUserName(String s) {
        int l = s.length();
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i < l; i++) {
            sb.append("*");
        }
        return s.substring(0, 1) + sb;
    }
}
