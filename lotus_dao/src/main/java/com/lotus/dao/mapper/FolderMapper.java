package com.lotus.dao.mapper;

import com.lotus.dao.pojo.Folder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FolderMapper extends com.lotus.dao.gmapper.FolderMapper {

    /**
     * 获取跟目录文件夹
     *
     * @return
     */
    Folder selectRootFolder();

    /**
     * 获取所有文件夹
     *
     * @return
     */
    List<Folder> selectAll();

    /**
     * 删除子文件夹的文件
     *
     * @param path
     */
    void deleteChildenFile(@Param("path") String path);

    /**
     * 删除自己的文件
     *
     * @param folderId
     */
    void deleteFile(@Param("folderId") Integer folderId);

    /**
     * 删除子文件夹
     *
     * @param path
     */
    void deleteChildrenFolder(@Param("path") String path);

    /**
     * 获取相同名字
     *
     * @param name
     * @return
     */
    String selectSameName(@Param("fullPath") String fullPath, @Param("name") String name, @Param("folderId") Integer folderId);
}