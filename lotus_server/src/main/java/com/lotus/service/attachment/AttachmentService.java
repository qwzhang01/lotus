package com.lotus.service.attachment;

import com.lotus.dao.pojo.Attachment;

import java.util.List;
import java.util.Map;

public interface AttachmentService {

    /**
     * 获取所有附件,附件不使用分页,模仿百度云
     *
     * @param mimtype 附件MIME Type
     * @param title   附件名称
     * @return
     */
    List<Map<String, Object>> findList(String mimtype, Integer folderId, String title);

    /**
     * 根据文件夹获取所有文件的子集
     *
     * @param mimtype
     * @param folderId
     * @return
     */
    List<Map<String, Object>> findListByFolder(String mimtype, Integer folderId);

    /**
     * 根据id获取详情
     *
     * @param fileId
     * @return
     */
    Attachment getById(Integer fileId);

    /**
     * 删除文件
     *
     * @param fileId
     * @return
     */
    boolean delete(Integer fileId);

    /**
     * 编辑文件夹
     *
     * @param attachment
     * @return
     */
    boolean update(Attachment attachment);

    List<Attachment> add(Integer folderId, String[] fileKeyAndNames);

    /**
     * 获取指定专辑的音乐
     *
     * @param mimeType
     * @param albumId
     * @return
     */
    List<Map<String, Object>> findList(String mimeType, Integer albumId);
}