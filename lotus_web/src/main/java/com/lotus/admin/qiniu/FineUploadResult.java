package com.lotus.admin.qiniu;

import com.lotus.common.entity.AjaxResult;

import java.io.Serializable;

public class FineUploadResult implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean success;
    private String src;

    public FineUploadResult(AjaxResult ajaxResult) {
        this.src = (String) ajaxResult.getData();
        this.success = ajaxResult.getErrorCode() == 0;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
