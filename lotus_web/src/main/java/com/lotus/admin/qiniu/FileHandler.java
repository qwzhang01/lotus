package com.lotus.admin.qiniu;

import com.google.gson.Gson;
import com.lotus.common.kit.StrKit;
import com.lotus.common.kit.TitleKit;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

public class FileHandler {

    protected static final Log log = LogFactory.getLog(FileHandler.class);

    public static String getToken(QiniuParam qiniuParam, String fileKey) {
        if (StrKit.isBlank(fileKey)) {
            fileKey = TitleKit.genTitleWithDateTime();
        }
        Auth auth = Auth.create(qiniuParam.getAK(), qiniuParam.getSK());
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(qiniuParam.getBUCKET(), null, expireSeconds, null);
        return upToken;
    }

    public static PutRet upload(QiniuParam qiniuParam, byte[] file, String name) {
        Configuration cfg = new Configuration(qiniuParam.getZONE());// 华南
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiniuParam.getAK(), qiniuParam.getSK());
        long expireSeconds = 3600;
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody",
                "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        String upToken = auth.uploadToken(qiniuParam.getBUCKET(), null, expireSeconds, putPolicy);

        try {
            Response response = uploadManager.put(file, name, upToken);
            PutRet putRet = new Gson().fromJson(response.bodyString(), PutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            log.error("上传至七牛失败：" + ExceptionUtils.getStackTrace(ex));
            return new PutRet(-1);
        }
    }

    public static PutRet upload(QiniuParam qiniuParam, String file, String name) {
        Configuration cfg = new Configuration(qiniuParam.getZONE());// 华南
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiniuParam.getAK(), qiniuParam.getSK());
        long expireSeconds = 3600;
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody",
                "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        String upToken = auth.uploadToken(qiniuParam.getBUCKET(), null, expireSeconds, putPolicy);
        if (StrKit.isBlank(name)) {
            name = TitleKit.genTitleWithDateTime();
        }
        String pref = file.substring(file.lastIndexOf("."), file.length());
        try {
            Response response = uploadManager.put(file, name + pref, upToken);
            PutRet putRet = new Gson().fromJson(response.bodyString(), PutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            log.error("上传至七牛失败：" + ExceptionUtils.getStackTrace(ex));
            return new PutRet(-1);
        }
    }

    public static PutRet upload(QiniuParam qiniuParam, File file, String name) {
        Configuration cfg = new Configuration(qiniuParam.getZONE());// 华南
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiniuParam.getAK(), qiniuParam.getSK());
        long expireSeconds = 3600;
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody",
                "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        String upToken = auth.uploadToken(qiniuParam.getBUCKET(), null, expireSeconds, putPolicy);
        if (StrKit.isBlank(name)) {
            name = TitleKit.genTitleWithDateTime();
        }
        String pref = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
        try {
            Response response = uploadManager.put(file, name + pref, upToken);
            PutRet putRet = new Gson().fromJson(response.bodyString(), PutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            log.error("上传至七牛失败：" + ExceptionUtils.getStackTrace(ex));
            return new PutRet(-1);
        }
    }

    public static PutRet delete(QiniuParam qiniuParam, String fileKey) {
        Configuration cfg = new Configuration(qiniuParam.getZONE());// 华南
        Auth auth = Auth.create(qiniuParam.getAK(), qiniuParam.getSK());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            Response response = bucketManager.delete(qiniuParam.getBUCKET(), fileKey);
            PutRet putRet = new Gson().fromJson(response.bodyString(), PutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            log.error("上传至七牛失败：" + ExceptionUtils.getStackTrace(ex));
            return new PutRet(-1);
        }
    }

    /**
     * 测试分片
     *
     * @param file
     * @param name
     * @return
     */
    public static PutRet uploadFP(QiniuParam qiniuParam, File file, String name) {
        if (StrKit.isBlank(name)) {
            name = TitleKit.genTitleWithDateTime();
        }

        Configuration cfg = new Configuration(qiniuParam.getZONE());// 华南
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiniuParam.getAK(), qiniuParam.getSK());
        long expireSeconds = 3600;

        // 设置转码操作参数
        String fops = "avthumb/m3u8/noDomain/1/segtime/5/ar/22050/acodec/libmp3lame/stripmeta/1";

        String urlbase64 = UrlSafeBase64.encodeToString(qiniuParam.getBUCKET() + ":" + name + ".m3u8");
        String pfops = fops + "|saveas/" + urlbase64;

        // 设置转码的队列
        String pipeline = "buddha";

        StringMap putPolicy = new StringMap();
        putPolicy//
                .put("returnBody",
                        "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}")//
                .putNotEmpty("persistentOps", pfops)//
                .putNotEmpty("persistentPipeline", pipeline);

        String upToken = auth.uploadToken(qiniuParam.getBUCKET(), null, expireSeconds, putPolicy);

        String pref = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());

        try {
            Response response = uploadManager.put(file, name + pref, upToken);
            PutRet putRet = new Gson().fromJson(response.bodyString(), PutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            log.error("上传至七牛失败：" + ExceptionUtils.getStackTrace(ex));
            return new PutRet(-1);
        }
    }
}
