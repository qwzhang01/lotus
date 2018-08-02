package com.lotus.admin.shiro;

import java.io.Serializable;

public class ShiroUser implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private boolean remberMe;
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isRemberMe() {
        return remberMe;
    }

    public void setRemberMe(boolean remberMe) {
        this.remberMe = remberMe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}