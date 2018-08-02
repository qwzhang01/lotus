package com.lotus.admin.beetl;

import java.text.DecimalFormat;

public class BeetlKit {

    public static String formatMoney(Object money) {
        if (money == null || money == "") {
            return "0.00";
        }
        if ("--".equals(money)) {
            return "--";
        }
        DecimalFormat d1 = new DecimalFormat("#,##0.##;(#)");
        return d1.format(money);
    }
}