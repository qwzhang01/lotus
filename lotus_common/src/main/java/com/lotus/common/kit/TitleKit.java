package com.lotus.common.kit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TitleKit {

    public static String genTitleWithDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        double random = Math.random();
        double ran2 = random * 100000;
        String ranStr = String.valueOf((int) ran2);
        return ranStr + format.format(new Date());
    }

    public static String sameTitle(String name) {
        if (name.endsWith(")") && name.contains("(")) {
            String num = name.substring(name.lastIndexOf("(") + 1, name.lastIndexOf(")"));
            try {
                int parseInt = Integer.parseInt(num);
                name = name.substring(0, name.lastIndexOf("(")) + "(" + (parseInt + 1) + ")";
            } catch (NumberFormatException e) {
                name = name + "(1)";
            }
        } else {
            name = name + "(1)";
        }
        return name;
    }

}
