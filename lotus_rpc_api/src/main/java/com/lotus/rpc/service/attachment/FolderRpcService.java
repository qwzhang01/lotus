package com.lotus.rpc.service.attachment;

import com.lotus.dao.pojo.Folder;

import java.util.List;

public interface FolderRpcService {
    /**
     * 获取所有文件夹
     *
     * @return
     */
    List<Folder> findAll();

    /**
     * 删除文件夹
     * 删除所有子文件夹的文件,删除自己的文件
     * 删除所有的子文件夹
     * 删除自己
     *
     * @param folderId
     * @return
     */
    boolean delete(Integer folderId);

    /**
     * 根据ID获取文件夹
     *
     * @param folderId
     * @return
     */
    Folder getById(Integer folderId);

    /**
     * 更新文件夹信息
     *
     * @param folder
     * @return
     */
    boolean update(Folder folder);

    /**
     * 新增文件夹
     *
     * @param folderId
     * @param name
     * @return
     */
    boolean add(Integer folderId, String name);

    /**
     * 获取跟目录
     *
     * @return
     */
    Folder getRootFolder();
}
