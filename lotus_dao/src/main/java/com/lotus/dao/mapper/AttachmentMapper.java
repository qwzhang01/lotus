package com.lotus.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttachmentMapper extends com.lotus.dao.gmapper.AttachmentMapper {

    /**
     * 获取附件列表
     *
     * @param minetype 附件MIME TYPE
     * @param fullPath 所在文件夹路径
     * @param title    文件标题
     * @return
     */
    List<Map<String, Object>> selectList(@Param("minetype") String minetype, @Param("folderId") Integer folderId, @Param("fullPath") String fullPath, @Param("title") String title);

    /**
     * 获取标题相同的文件
     *
     * @param folderId
     * @param title
     * @param fileId
     * @return
     */
    String selectSameTitle(@Param("folderId") Integer folderId, @Param("title") String title, @Param("fileId") Integer fileId);

    /**
     * 获取指定专辑的曲目
     */
    List<Map<String, Object>> selectListByAlbumId(@Param("minetype") String mimeType, @Param("albumId") Integer albumId);
}