package com.lotus.admin.qiniu;

import java.io.Serializable;

public class QiNiuToken implements Serializable {

    private String uptoken;

    public QiNiuToken(String uptoken) {
        this.uptoken = uptoken;
    }

    public String getUptoken() {
        return uptoken;
    }

    public void setUptoken(String uptoken) {
        this.uptoken = uptoken;
    }
}
