package com.lotus.service.content;

import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Content;

import java.util.Date;
import java.util.Map;

/**
 * 简单需求解释
 * 内容在ＡＰＰ上按照栏目分，ＡＰＰ的栏目有　文章　推荐　频道
 * 内容在后台按照taxonomy分类，每类taxonomy对应不同的 APP栏目以及 内容类型（文字 或 音频）
 * 每类taxonomy下面对应文章内容/或音乐专辑
 * 文章内容不再细分，音乐专辑对应具体的音乐
 */
public interface ContentService {

    /**
     * 根据类型获取对应内容列表
     *
     * @param appColumn  APP 栏目
     * @param sticky     sticky 0获取不置顶 1获取置顶内容
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<Map<String, Object>> findPageList(String appColumn, Integer sticky, Integer pageNumber, Integer pageSize);

    /**
     * 后台分页查询
     *
     * @param title
     * @param begin
     * @param end
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<Map<String, Object>> findList(String contentType, Integer taxonomyId, String title, Date begin, Date end, Integer pageNumber, Integer pageSize);


    /**
     * 根据ID获取实体
     *
     * @param contentId
     * @return
     */
    Content getById(Integer contentId);

    Boolean update(Content content);

    Boolean add(Content content);

    Boolean delete(Content content);

    boolean reSort(Integer articleId, boolean equals);

    Boolean update(Content content, Integer[] audioId);

    Boolean add(Content content, Integer[] audioId);
}
