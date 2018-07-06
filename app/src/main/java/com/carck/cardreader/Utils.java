package com.carck.cardreader;

public class Utils {
    private static String HEX_CHARS = "0123456789ABCDEF";

    static  String TAG = "Host Card Emulator";
    static  String STATUS_SUCCESS = "9000";
    static String STATUS_FAILED = "6F00";
    static  String CLA_NOT_SUPPORTED = "6E00";
    static String INS_NOT_SUPPORTED = "6D00";
    static String AID = "A0000002471001";
    static String SELECT_INS = "A4";
    static String DEFAULT_CLA = "00";
    static int MIN_APDU_LENGTH = 12;

    static byte[] hexStringToByteArray(String data)  {

        byte[] result = new byte[(data.length() / 2)];

        for (int i=0;i<data.length() ;i+=2 ) {
            int firstIndex = HEX_CHARS.indexOf(data.charAt(i));
            int secondIndex = HEX_CHARS.indexOf(data.charAt(i + 1));

            int octet = firstIndex<<(4)|(secondIndex);
            result[i>>1]= (byte)octet;
        }

        return result;
    }

    private static char[] HEX_CHARS_ARRAY = "0123456789ABCDEF".toCharArray();
    static String toHex(byte[] byteArray)  {
        StringBuilder result = new StringBuilder();

        for (byte octet : byteArray) {
            int firstIndex = (octet & 0xF0) >> (4);
            int secondIndex = octet & 0x0F;
            result.append(HEX_CHARS_ARRAY[firstIndex]);
            result.append(HEX_CHARS_ARRAY[secondIndex]);
        }

        return result.toString();
    }
}
