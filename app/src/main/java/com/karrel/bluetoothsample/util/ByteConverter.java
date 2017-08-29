package com.karrel.bluetoothsample.util;

import android.annotation.SuppressLint;

/**
 * Created by Rell on 2017. 8. 28..
 */

public class ByteConverter {

    @SuppressLint("DefaultLocale")
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(byteToHex(b[i]));
        }
        return sb.toString().toUpperCase();
    }

    public static String byteToHex(byte b) {
        StringBuffer sb = new StringBuffer(2);
        int v = b & 0xff;
        if (v < 16) {
            sb.append('0');
        }
        sb.append(Integer.toHexString(v));
        return sb.toString();
    }

    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }

    public static byte[] hexToByteArray(int hex) {
        return hexToByteArray(Integer.toHexString(hex));
    }

    public static int hexToInteger(String str) {
        return Integer.valueOf(str, 16);
    }

    public static String hexToBit(String hex) {
        String str = "";
        String tmpStr;
        int tmpI;

        StringBuffer strBuffer = new StringBuffer(hex);
        for (int i = 0; i < hex.length() / 2; i++) {
            tmpStr = strBuffer.substring(2 * i, 2 * i + 2);
            tmpI = Integer.valueOf(tmpStr, 16);

            String tmpStr2 = Integer.toString(tmpI, 2);
            tmpStr2 = String.format("%8s", tmpStr2).replace(' ', '0');
        }

        return str;
    }

    public static String byteToBit(byte[] bytesArray) {

        if (bytesArray == null) {
            return "";
        }

        String s = "";
        for (byte b : bytesArray) {
            s += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }

        return s.trim();
    }

    public static String asciiToBinary(String asciiString) {

        byte[] bytes = asciiString.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            // binary.append(' ');
        }
        return binary.toString();
    }

    public static String asciiToHex(String str) {

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString();
    }

    public static String hexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        // 49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            // grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            // convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            // convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }
        System.out.println("Decimal : " + temp.toString());

        return sb.toString();
    }

    public static String integerToHex(int hexCommand) {
        return byteArrayToHexString(hexToByteArray(hexCommand));
    }
}
