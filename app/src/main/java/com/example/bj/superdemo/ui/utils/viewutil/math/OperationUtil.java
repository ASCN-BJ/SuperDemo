package com.example.bj.superdemo.ui.utils.viewutil.math;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016-12-5.
 * Description：
 */

public class OperationUtil {

    //    public static double mutilAdd(double... args) {
//        List<BigDecimal> mAllLists = new ArrayList<>();
//        for (double x : args) {
//            BigDecimal bd = new BigDecimal(Double.toString(x));
//            mAllLists.add(bd);
//        }
//        BigDecimal bigDecimal = null;
//        for (int x = 0; x < mAllLists.size(); x++) {
//            if (bigDecimal==null){
//                bigDecimal=mAllLists.get(x);
//            }else {
//                bigDecimal = bigDecimal.add(mAllLists.get(x));
//            }
//
//        }
//        return bigDecimal.doubleValue();
//    }
    public static double mutilAdd(double... args) {
        BigDecimal bd = null;
        for (int x = 0; x < args.length; x++) {
            if (bd == null) {
                bd = new BigDecimal(Double.toString(args[x]));
            } else {
                BigDecimal childBd = new BigDecimal(Double.toString(args[x]));
                bd = bd.add(childBd);
            }
        }
        return bd.doubleValue();
    }


    /**
     * 加
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public static double add(double arg1, double arg2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
        BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
        return bd1.add(bd2).doubleValue();
    }

    /**
     * 减
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public double sub(double arg1, double arg2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
        BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
        return bd1.subtract(bd2).doubleValue();
    }

    /**
     * 乘
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public double multi(double arg1, double arg2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
        BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
        return bd1.multiply(bd2).doubleValue();
    }

    /**
     * 除
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public double divide(double arg1, double arg2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
        BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
        return bd1.divide(bd2).doubleValue();
    }


}
