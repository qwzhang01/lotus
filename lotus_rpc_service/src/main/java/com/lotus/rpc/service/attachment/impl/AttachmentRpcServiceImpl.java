package com.lotus.rpc.service.attachment.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.dao.pojo.Attachment;
import com.lotus.rpc.service.attachment.AttachmentRpcService;
import com.lotus.service.attachment.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
@Service(version = "1.0.0")
public class AttachmentRpcServiceImpl implements AttachmentRpcService {

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 获取所有附件,附件不使用分页,模仿百度云
     *
     * @param mimtype 附件MIME Type
     * @param title   附件名称
     * @return
     */
    @Override
    public List<Map<String, Object>> findList(String mimtype, Integer folderId, String title) {
        return attachmentService.findList(mimtype, folderId, title);
    }

    /**
     * 根据文件夹获取所有文件的子集
     *
     * @param mimtype
     * @param folderId
     * @return
     */
    @Override
    public List<Map<String, Object>> findListByFolder(String mimtype, Integer folderId) {
        return attachmentService.findListByFolder(mimtype, folderId);

    }

    /**
     * 根据id获取详情
     *
     * @param fileId
     * @return
     */
    @Override
    public Attachment getById(Integer fileId) {
        return attachmentService.getById(fileId);
    }

    /**
     * 删除文件
     *
     * @param fileId
     * @return
     */
    @Override
    public boolean delete(Integer fileId) {
        return attachmentService.delete(fileId);
    }

    /**
     * 编辑文件夹
     *
     * @param attachment
     * @return
     */
    @Override
    public boolean update(Attachment attachment) {
        return attachmentService.update(attachment);
    }

    @Override
    public List<Attachment> add(Integer folderId, String[] fileKeyAndNames) {
        return attachmentService.add(folderId, fileKeyAndNames);
    }

    /**
     * 获取指定专辑的音乐
     *
     * @param mimeType
     * @param albumId
     * @return
     */
    @Override
    public List<Map<String, Object>> findList(String mimeType, Integer albumId) {
        return attachmentService.findList(mimeType, albumId);
    }
}