package com.lotus.rpc.service.content.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Content;
import com.lotus.rpc.service.content.ContentRpcService;
import com.lotus.service.content.ContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * 简单需求解释
 * 内容在ＡＰＰ上按照栏目分，ＡＰＰ的栏目有　文章　推荐　频道
 * 内容在后台按照taxonomy分类，每类taxonomy对应不同的 APP栏目以及 内容类型（文字 或 音频）
 * 每类taxonomy下面对应文章内容/或音乐专辑
 * 文章内容不再细分，音乐专辑对应具体的音乐
 */
@Service(version = "1.0.0")
public class ContentRpcServiceImpl implements ContentRpcService {

    @Autowired
    private ContentService contentService;

    /**
     * 根据类型获取对应内容列表
     *
     * @param appColumn  APP 栏目
     * @param sticky     sticky 0获取不置顶 1获取置顶内容
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Page<Map<String, Object>> findPageList(String appColumn, Integer sticky, Integer pageNumber, Integer pageSize) {
        return contentService.findPageList(appColumn, sticky, pageNumber, pageSize);
    }

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
    @Override
    public Page<Map<String, Object>> findList(String contentType, Integer taxonomyId, String title, Date begin, Date end, Integer pageNumber, Integer pageSize) {
        return contentService.findList(contentType, taxonomyId, title, begin, end, pageNumber, pageSize);
    }


    /**
     * 根据ID获取实体
     *
     * @param contentId
     * @return
     */
    @Override
    public Content getById(Integer contentId) {
        return contentService.getById(contentId);
    }

    @Override
    public Boolean update(Content content) {
        return contentService.update(content);
    }

    @Override
    public Boolean add(Content content) {
        return contentService.add(content);
    }

    @Override
    public Boolean delete(Content content) {
        return contentService.delete(content);
    }

    @Override
    public boolean reSort(Integer articleId, boolean equals) {
        return contentService.reSort(articleId, equals);
    }

    @Override
    public Boolean update(Content content, Integer[] audioId) {
        return contentService.update(content, audioId);
    }

    @Override
    public Boolean add(Content content, Integer[] audioId) {
        return contentService.add(content, audioId);
    }
}
