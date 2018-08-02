package com.lotus.admin.qiniu;

public class PutRet {
    private int code;
    private String key;
    private String hash;
    private String bucket;
    private long fsize;

    public PutRet(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public long getFsize() {
        return fsize;
    }

    public void setFsize(long fsize) {
        this.fsize = fsize;
    }
}