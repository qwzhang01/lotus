package com.lotus.service.content.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.PageKit;
import com.lotus.dao.mapper.AttachmentMapper;
import com.lotus.dao.mapper.AudioAttachmentMapper;
import com.lotus.dao.mapper.ContentMapper;
import com.lotus.dao.pojo.AudioAttachment;
import com.lotus.dao.pojo.Content;
import com.lotus.service.content.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("contentService")
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private AudioAttachmentMapper audioAttachmentMapper;

    /**
     * 根据类型获取对应内容列表
     *
     * @param appColumn  APP栏目
     * @param sticky     0获取不置顶 1获取置顶内容
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Page<Map<String, Object>> findPageList(String appColumn, Integer sticky, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Map<String, Object>> list = contentMapper.selectList(appColumn, sticky);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return PageKit.pageComplete(pageInfo);
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
        PageHelper.startPage(pageNumber, pageSize);
        List<Map<String, Object>> list = contentMapper.selectListAdmin(contentType, taxonomyId, title, begin, end);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return PageKit.pageComplete(pageInfo);
    }

    /**
     * 根据ID获取实体
     *
     * @param contentId
     * @return
     */
    @Override
    public Content getById(Integer contentId) {
        return contentMapper.selectByPrimaryKey(contentId);
    }

    @Override
    public Boolean update(Content content) {
        content.setModified(new Date());
        return contentMapper.updateByPrimaryKeySelective(content) == 1;
    }

    @Override
    @Transactional
    public Boolean update(Content content, Integer[] audioIds) {
        Boolean update = update(content);
        audioAttachmentMapper.deleteByContentId(content.getId());
        if (audioIds != null && audioIds.length > 0) {
            for (Integer audioId : audioIds) {
                if (audioId != null && audioId != 0) {
                    AudioAttachment aa = new AudioAttachment();
                    aa.setContentId(content.getId());
                    aa.setAttachmentId(audioId);
                    audioAttachmentMapper.insertSelective(aa);
                }
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean add(Content content, Integer[] audioIds) {
        Boolean add = add(content);
        if (audioIds != null && audioIds.length > 0) {
            for (Integer audioId : audioIds) {
                AudioAttachment aa = new AudioAttachment();
                aa.setContentId(content.getId());
                aa.setAttachmentId(audioId);
                audioAttachmentMapper.insertSelective(aa);
            }
        }
        return true;
    }

    @Override
    public Boolean add(Content content) {
        //获取排序最大的数字
        Integer sortNum = contentMapper.selectMaxSortNum(content.getTaxonomyId());
        if(sortNum == null) {
            sortNum = 0;
        }
        content.setSortNum(sortNum + 1);
        content.setIsPublished(1);
        content.setCreated(new Date());
        content.setModified(new Date());
        return contentMapper.insertSelective(content) == 1;
    }

    @Override
    @Transactional
    public Boolean delete(Content content) {
        audioAttachmentMapper.deleteByContentId(content.getId());
        return contentMapper.deleteByPrimaryKey(content.getId()) == 1;
    }

    @Override
    @Transactional
    public boolean reSort(Integer articleId, boolean isUp) {
        Content mContent = contentMapper.selectByPrimaryKey(articleId);
        if (mContent == null) {
            return false;
        }
        Content tContent = null;
        if (isUp) {// 上升
            tContent = contentMapper.selectNeighbor("up",
                    mContent.getSortNum(), mContent.getTaxonomyId());
        } else {
            tContent = contentMapper.selectNeighbor("down",
                    mContent.getSortNum(), mContent.getTaxonomyId());
        }
        if (tContent == null) {
            return false;
        }
        int sorNumTmp = mContent.getSortNum();
        mContent.setSortNum(tContent.getSortNum());
        tContent.setSortNum(sorNumTmp);
        int count1 = contentMapper.updateByPrimaryKeySelective(mContent);
        int count2 = contentMapper.updateByPrimaryKeySelective(tContent);
        return (count1 == 1) && (count2 == 1);
    }
}