package com.lotus.admin.web;

import com.lotus.admin.qiniu.*;
import com.lotus.common.entity.AjaxResult;
import com.lotus.common.kit.TitleKit;
import com.lotus.rpc.service.attachment.FolderRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    @Resource
    private QiniuParam qiniuParam;
    @Autowired
    private FolderRpcService folderService;

    /**
     * fine-upload 上传插件页面
     *
     * @return
     */
    @RequestMapping(value = "/img", method = RequestMethod.GET)
    public String img() {
        return "upload/img";
    }

    @ResponseBody
    @RequestMapping(value = "/img/doUpload", method = RequestMethod.POST)
    public FineUploadResult upload(HttpServletRequest request) {

        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest fileRequest = resolver.resolveMultipart(request);

        List<MultipartFile> files = fileRequest.getFiles("qqfile");
        if (files == null || files.size() <= 0) {
            return new FineUploadResult(AjaxResult.error("上传文件为空"));
        }
        MultipartFile file = files.get(0);
        String name = file.getOriginalFilename();
        String pref = name.substring(name.lastIndexOf("."), name.length());
        name = TitleKit.genTitleWithDateTime() + pref;
        try {
            byte[] fileBytes = file.getBytes();
            PutRet upload = FileHandler.upload(qiniuParam, fileBytes, name);
            return new FineUploadResult(AjaxResult.success("上传成功", upload.getKey()));
        } catch (IOException e) {
            return new FineUploadResult(AjaxResult.error(e.getMessage()));
        }
    }

    /**
     * 七牛上传插件页面
     *
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(@RequestParam(value = "folderId", required = false) Integer folderId, Model model) {
        if (folderId == null || folderId == 0) {
            folderId = folderService.getRootFolder().getId();
        }
        model.addAttribute("folderId", folderId);
        return "upload/upload";
    }

    /**
     * 获取七牛上传token
     *
     * @param fileKey
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public QiNiuToken getToken(@RequestParam(value = "fileKey", required = false) String fileKey) {
        String tokenStr = FileHandler.getToken(qiniuParam, fileKey);
        QiNiuToken token = new QiNiuToken(tokenStr);
        return token;
    }
}
