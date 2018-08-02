package com.lotus.rpc.service.content;

import com.lotus.common.entity.Page;

import java.util.Map;

public interface AudioAttachmentRpcService {

    /**
     * 根据音乐专辑ID获取对应音乐播放列表
     *
     * @param contentId  音乐专辑ID
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<Map<String, Object>> findPageList(Integer contentId, Integer pageNumber, Integer pageSize);

    /**
     * 根据音频ID获取音频详情
     *
     * @param audioId
     * @return
     */
    Map<String, Object> getAudioDetail(Integer audioId);
}
