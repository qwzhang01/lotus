package com.lotus.service.content.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.PageKit;
import com.lotus.dao.mapper.AudioAttachmentMapper;
import com.lotus.service.content.AudioAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AudioAttachmentServiceImpl implements AudioAttachmentService {

    @Autowired
    private AudioAttachmentMapper audioAttachmentMapper;

    /**
     * 获取指定栏目的随机一个专辑
     * @param appColumn
     * @return
     */
    private Integer getRandomByAppColumn(String appColumn){
          Integer  contentId = audioAttachmentMapper.selectRandom(appColumn);
          return contentId;
    }

    /**
     * 根据音乐专辑ID获取对应音乐播放列表
     *
     * @param contentId  音乐专辑ID
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Page<Map<String, Object>> findPageList(Integer contentId, Integer pageNumber, Integer pageSize) {

        if(-1 == contentId){
            contentId = getRandomByAppColumn("recomm");
        }

        PageHelper.startPage(pageNumber, pageSize);
        List<Map<String, Object>> list = audioAttachmentMapper.selectList(contentId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return PageKit.pageComplete(pageInfo);
    }

    /**
     * 根据音频ID获取音频详情
     *
     * @param audioId
     * @return
     */
    @Override
    public Map<String, Object> getAudioDetail(Integer audioId) {
        Map<String, Object> detail = audioAttachmentMapper.selectById(audioId);
        return detail;
    }
}