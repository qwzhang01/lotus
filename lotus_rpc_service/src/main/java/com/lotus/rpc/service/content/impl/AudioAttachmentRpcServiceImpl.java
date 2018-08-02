package com.lotus.rpc.service.content.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.common.entity.Page;
import com.lotus.rpc.service.content.AudioAttachmentRpcService;
import com.lotus.service.content.AudioAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Service(version = "1.0.0")
public class AudioAttachmentRpcServiceImpl implements AudioAttachmentRpcService {

    @Autowired
    private AudioAttachmentService audioAttachmentService;

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
        return audioAttachmentService.findPageList(contentId, pageNumber, pageSize);
    }

    /**
     * 根据音频ID获取音频详情
     *
     * @param audioId
     * @return
     */
    @Override
    public Map<String, Object> getAudioDetail(Integer audioId) {
        return audioAttachmentService.getAudioDetail(audioId);
    }
}
