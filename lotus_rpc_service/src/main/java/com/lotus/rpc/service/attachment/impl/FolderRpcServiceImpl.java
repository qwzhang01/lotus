package com.lotus.rpc.service.attachment.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.dao.pojo.Folder;
import com.lotus.rpc.service.attachment.FolderRpcService;
import com.lotus.service.attachment.FolderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author qwzh110
 */
@Service(version = "1.0.0")
public class FolderRpcServiceImpl implements FolderRpcService {
    @Autowired
    private FolderService folderService;

    /**
     * 获取所有文件夹
     *
     * @return
     */
    @Override
    public List<Folder> findAll() {
        return folderService.findAll();
    }

    /**
     * 删除文件夹
     * 删除所有子文件夹的文件,删除自己的文件
     * 删除所有的子文件夹
     * 删除自己
     *
     * @param folderId
     * @return
     */
    @Override
    public boolean delete(Integer folderId) {
        return folderService.delete(folderId);
    }

    /**
     * 根据ID获取文件夹
     *
     * @param folderId
     * @return
     */
    @Override
    public Folder getById(Integer folderId) {
        return folderService.getById(folderId);
    }

    /**
     * 更新文件夹信息
     *
     * @param folder
     * @return
     */
    @Override
    public boolean update(Folder folder) {
        return folderService.update(folder);
    }

    /**
     * 新增文件夹
     *
     * @param folderId
     * @param name
     * @return
     */
    @Override
    public boolean add(Integer folderId, String name) {
        return folderService.add(folderId, name);
    }

    /**
     * 获取跟目录
     *
     * @return
     */
    @Override
    public Folder getRootFolder() {
        return folderService.getRootFolder();
    }
}
