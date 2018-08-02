package com.lotus.service.attachment.impl;

import com.lotus.common.kit.StrKit;
import com.lotus.common.kit.TitleKit;
import com.lotus.dao.mapper.AttachmentMapper;
import com.lotus.dao.mapper.FolderMapper;
import com.lotus.dao.pojo.Attachment;
import com.lotus.dao.pojo.Folder;
import com.lotus.service.attachment.AttachmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private FolderMapper folderMapper;

    @Override
    public List<Map<String, Object>> findList(String mimetype, Integer folderId, String title) {
        //搜索条件下不显示文件夹
        if (StringUtils.isNoneBlank(title)) {
            return findListByTitle(mimetype, title);
        }
        //搜索文件夹以及文件
        Folder folder = getFolderByIdAnyWay(folderId);
        List<Map<String, Object>> list = attachmentMapper.selectList(mimetype, folder.getId(), folder.getFullPath() + folder.getId() + "/", null);
        return list;
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
        //搜索文件夹以及文件
        Folder folder = folderMapper.selectByPrimaryKey(folderId);
        List<Map<String, Object>> list = attachmentMapper.selectList(mimtype, folder.getId(), folder.getFullPath() + folder.getId() + "/", null);
        return list;
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
        return attachmentMapper.selectListByAlbumId(mimeType, albumId);
    }

    @Override
    public Attachment getById(Integer fileId) {
        return attachmentMapper.selectByPrimaryKey(fileId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer fileId) {
        int count = attachmentMapper.deleteByPrimaryKey(fileId);
        return 1 == count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Attachment attachment) {
        String title = attachmentMapper.selectSameTitle(attachment.getFolderId(), attachment.getTitle(), attachment.getId());
        if (StrKit.notBlank(title)) {
            attachment.setTitle(TitleKit.sameTitle(title));
        }
        int count = attachmentMapper.updateByPrimaryKeySelective(attachment);
        return count == 1;
    }

    /**
     * @param folderId        曲目所在文件夹
     * @param fileKeyAndNames 曲目名称与曲目七牛key,格式为key|name
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Attachment> add(Integer folderId, String[] fileKeyAndNames) {
        List<Attachment> list = new ArrayList<>();
        for (String nameAndPath : fileKeyAndNames) {
            if (StrKit.notBlank(nameAndPath)) {
                String[] namePathArray = nameAndPath.split("\\|");
                if (namePathArray != null && namePathArray.length > 1) {
                    Attachment tmpAtt = new Attachment();
                    tmpAtt.setFolderId(folderId);
                    tmpAtt.setPath(namePathArray[0]);
                    String title = attachmentMapper.selectSameTitle(folderId, namePathArray[1], null);
                    if (StrKit.notBlank(title)) {
                        title = TitleKit.sameTitle(title);
                    } else {
                        title = namePathArray[1];
                    }
                    tmpAtt.setTitle(title);
                    attachmentMapper.insertSelective(tmpAtt);
                    list.add(tmpAtt);
                }
            }
        }
        return list;
    }

    private Folder getFolderByIdAnyWay(Integer folderId) {
        if (0 == folderId) {
            return getRootFolder();
        }
        return folderMapper.selectByPrimaryKey(folderId);
    }

    private Folder getRootFolder() {
        return folderMapper.selectRootFolder();
    }

    private List<Map<String, Object>> findListByTitle(String minetype, String title) {
        return attachmentMapper.selectList(minetype, null, null, title);
    }
}
