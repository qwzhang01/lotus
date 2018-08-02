package com.lotus.service.attachment.impl;

import com.lotus.common.kit.StrKit;
import com.lotus.common.kit.TitleKit;
import com.lotus.dao.mapper.FolderMapper;
import com.lotus.dao.pojo.Folder;
import com.lotus.service.attachment.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderMapper folderMapper;

    /**
     * 获取所有文件夹
     *
     * @return
     */
    @Override
    public List<Folder> findAll() {
        return folderMapper.selectAll();
    }

    @Override
    @Transactional
    public boolean delete(Integer folderId) {
        Folder folder = folderMapper.selectByPrimaryKey(folderId);
        if (folder == null) {
            return false;
        }
        folderMapper.deleteChildenFile(folder.getFullPath() + folder.getId() + "/");//删除子文件夹的文件
        folderMapper.deleteFile(folder.getId());//删除自己的文件
        folderMapper.deleteChildrenFolder(folder.getFullPath() + folder.getId() + "/");//删除子文件夹
        int count = folderMapper.deleteByPrimaryKey(folderId);//删除自己
        return count == 1;
    }

    /**
     * 根据ID获取文件夹
     *
     * @param folderId
     * @return
     */
    @Override
    public Folder getById(Integer folderId) {
        return folderMapper.selectByPrimaryKey(folderId);
    }

    @Override
    @Transactional
    public boolean update(Folder folder) {
        String sameName = folderMapper.selectSameName(folder.getFullPath(), folder.getName(), folder.getId());
        if (StrKit.notBlank(sameName)) {
            folder.setName(TitleKit.sameTitle(sameName));
        }
        int count = folderMapper.updateByPrimaryKey(folder);
        return count == 1;
    }

    @Override
    @Transactional
    public boolean add(Integer pFolderId, String name) {
        Folder pFolder = null;
        if (pFolderId == null || pFolderId == 0) {
            pFolder = folderMapper.selectRootFolder();
        } else {
            pFolder = folderMapper.selectByPrimaryKey(pFolderId);
        }
        String newPaht = pFolder.getFullPath() + pFolder.getId() + "/";
        String sameName = folderMapper.selectSameName(pFolder.getFullPath() + pFolder.getId() + "/", name, null);
        if (StrKit.notBlank(sameName)) {
            name = TitleKit.sameTitle(sameName);
        }
        Folder folder = new Folder();
        folder.setName(name);
        folder.setFullPath(newPaht);
        int count = folderMapper.insert(folder);
        return count == 1;
    }

    /**
     * 获取跟目录
     *
     * @return
     */
    @Override
    public Folder getRootFolder() {
        return folderMapper.selectRootFolder();
    }
}
