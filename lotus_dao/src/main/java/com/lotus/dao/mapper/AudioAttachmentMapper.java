package com.lotus.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AudioAttachmentMapper extends com.lotus.dao.gmapper.AudioAttachmentMapper {

    /**
     * 分页获取指定专辑的音频列表
     *
     * @param contentId
     * @return
     */
    List<Map<String, Object>> selectList(@Param("contentId") Integer contentId);

    /**
     * 获取指定的音频详情
     *
     * @param audioId
     * @return
     */
    Map<String, Object> selectById(Integer audioId);

    /**
     * 根据app栏目随机获取专辑ID
     *
     * @param appColumn
     * @return
     */
    Integer selectRandom(@Param("appColumn") String appColumn);

    void deleteByContentId(@Param("contentId") Integer contentId);
}