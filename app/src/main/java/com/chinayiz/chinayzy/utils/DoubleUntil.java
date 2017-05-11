package com.chinayiz.chinayzy.utils;

import java.math.BigDecimal;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/5/11 14:34
 * Class DoubleUntil
 */

public class DoubleUntil {
    /**
     * double 相加
     * @param d1
     * @param d2
     * @return
     */
    public  static  double sum(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2).doubleValue();
    }


    /**
     * double 相减
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }
}
