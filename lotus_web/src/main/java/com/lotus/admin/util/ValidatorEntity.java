package com.lotus.admin.util;


import java.io.Serializable;

public class ValidatorEntity implements Serializable {
    private boolean valid;

    public static ValidatorEntity success() {
        ValidatorEntity entity = new ValidatorEntity();
        entity.setValid(true);
        return entity;
    }

    public static ValidatorEntity error() {
        ValidatorEntity entity = new ValidatorEntity();
        entity.setValid(false);
        return entity;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
