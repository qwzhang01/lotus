package com.lotus.common.kit;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

public class SecretKit {

    public static boolean validateAPIRequest(String appkey, String timestamp, String signature, String token) {
        if (StringUtils.isBlank(appkey) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(signature)) {
            return false;
        }
        Timestamp rTime = new Timestamp(Long.valueOf(timestamp));
        Date now = new Date();
        if (rTime.compareTo(now) < 600000 && now.compareTo(rTime) < 600000) {
            String crypt = CryptKit.sha1(appkey + timestamp + token);
            return signature.equals(crypt);
        }
        return false;
    }
}
