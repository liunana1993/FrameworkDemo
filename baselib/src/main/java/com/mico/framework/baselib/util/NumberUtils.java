package com.mico.framework.baselib.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NumberUtils {
    /**
     * 保留scale位小数.
     *
     * @param value
     * @param scale
     * @return
     */
    public static double getRoundValue(double value, int scale) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        bigDecimal = bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static boolean isEqual(int num1, int num2) {
        return num1 == num2;
    }

    /**
     * 保留两位小数
     *
     * @param value
     * @return
     */
    public static String formatNumberReserveTwo(double value) {
        // NumberFormat nf = NumberFormat.getNumberInstance();
        // nf.setMaximumFractionDigits(2);
        // return nf.format(value);

        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(value);
    }

    public static <T extends Number> ArrayList<T> tokenizeToNumberList(String str, String delimiters, boolean trimTokens,
                                                                       boolean ignoreEmptyTokens, Class<T> clazz) {
        if (str == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        ArrayList<T> tokens = new ArrayList<T>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                T t = null;
                if (Integer.class.equals(clazz)) {
                    t = (T) Integer.valueOf(token);
                } else if (Long.class.equals(clazz)) {
                    t = (T) Long.valueOf(token);
                } else if (Float.class.equals(clazz)) {
                    t = (T) Float.valueOf(token);
                } else if (Double.class.equals(clazz)) {
                    t = (T) Double.valueOf(token);
                } else {
                    throw new RuntimeException("Must be Integer,Long,Float or Double type.");
                }
                tokens.add(t);
            }
        }
        return tokens;
    }

    public static <T extends Number> ArrayList<T> tokenizeToNumberList(String str, String delimiters, Class<T> clazz) {
        return tokenizeToNumberList(str, delimiters, true, true, clazz);
    }

    /**
     * 获取索引的位置.
     *
     * @param indexCount 索引的总数.
     * @param index      索引值，可能超出范围.
     * @return 索引
     */
    public static int getIndexInRange(int indexCount, int index) {
        if (index > 0) {
            int tmp = index % indexCount;
            return tmp;
        } else {
            int tmp = Math.abs(index);
            tmp = tmp % indexCount;
            tmp = indexCount - tmp;
            return tmp;
        }
    }
}
