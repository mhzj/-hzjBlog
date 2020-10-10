package com.example.demo.utils;

public class UnicodeGenerate {

    public static void main(String[] args) {
        // write your code here
        String s = "CLS(34);SBC(34);DS16(260,45,'有效期',15);DS16(110,160,'规格',15);DS16(110,190,'批号',15);DS16(110,280,'库存',15);ELABL(16,330,45,459,95,'20190806',15,0,1,2);";
        String s1 = gbEncoding(s);
        System.out.println(spaceAt2(s1));

    }

    //文字转为十六进制
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + hexB;
            // unicodeBytes = unicodeBytes + "//u" + hexB;
        }

        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    //字符串空格隔开
    public static String spaceAt2(String str) {

        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i += 2) {
            sb.append(str.substring(i, i + 2)).append(" ");
        }
        return sb.toString();
    }


    //十六进制转换为文字
    public static String decodeUnicode(final String dataStr) {

        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}

