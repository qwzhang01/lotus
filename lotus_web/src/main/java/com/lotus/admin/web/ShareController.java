package com.lotus.admin.web;

import com.lotus.common.entity.AjaxResult;
import com.lotus.common.kit.StrKit;
import com.lotus.dao.pojo.Content;
import com.lotus.rpc.service.attachment.AttachmentRpcService;
import com.lotus.rpc.service.content.AudioAttachmentRpcService;
import com.lotus.rpc.service.content.ContentRpcService;
import com.lotus.rpc.service.system.OptionRpcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ContentRpcService contentService;
    @Autowired
    private AttachmentRpcService attachmentService;
    @Autowired
    private AudioAttachmentRpcService audioAttachmentService;
    @Autowired
    private OptionRpcService optionService;

    @RequestMapping("/article/{shareId}")
    public String article(@PathVariable("shareId") String shareId, Model model) {
        Integer articleId = 0;
        Integer isShare = 0;
        if (shareId.contains("-")) {
            articleId = Integer.parseInt(shareId.substring(0, shareId.indexOf("-")));
            isShare = Integer.parseInt(shareId.substring(1 + shareId.indexOf("-")));
        } else {
            articleId = Integer.parseInt(shareId);
        }
        Content article = contentService.getById(articleId);
        model.addAttribute("article", article);
        if (1 == isShare) {
            model.addAttribute("share", true);
        } else {
            model.addAttribute("share", false);
        }
        return "share/article";
    }

    @RequestMapping("/album/{albumId}")
    public String album(@PathVariable("albumId") Integer albumId, Model model) {
        Content content = contentService.getById(albumId);
        model.addAttribute("content", content);

        List<Map<String, Object>> list = attachmentService.findList("", content.getId());
        model.addAttribute("audioList", list);

        return "share/album";
    }

    /**
     * 不带播放列表的曲目分享页面
     *
     * @param audioId
     * @param model
     * @return
     */
    @RequestMapping("/audio/{audioId}")
    public String audio(@PathVariable("audioId") Integer audioId, Model model) {
        Map<String, Object> audio = audioAttachmentService.getAudioDetail(audioId);
        String thumbnail = (String) audio.get("thumbnail");
        if (StrKit.isBlank(thumbnail)) {
            audio.put("thumbnail", optionService.get("default_thumbnail"));
        }
        model.addAttribute("audio", audio);
        return "share/audio";
    }

    /**
     * 带播放列表的曲目分享页面
     *
     * @param audioId
     * @param model
     * @return
     */
    @RequestMapping("/audio/with/list/{audioId}")
    public String audioWithAlbum(@PathVariable("audioId") Integer audioId, Model model) {
        Map<String, Object> audio = audioAttachmentService.getAudioDetail(audioId);
        String thumbnail = (String) audio.get("thumbnail");
        if (StrKit.isBlank(thumbnail)) {
            audio.put("thumbnail", optionService.get("default_thumbnail"));
        }
        model.addAttribute("audio", audio);

        return "share/audio_list";
    }

    /**
     * 异步获取指定专辑的所有曲目
     * @param albumId
     * @return
     */
    @ResponseBody
    @RequestMapping("/album/with/{albumId}")
    public AjaxResult album(@PathVariable("albumId") Integer albumId) {

        List<Map<String, Object>> list = attachmentService.findList("", albumId);
        String defPath = optionService.get("default_thumbnail");
        for (Map<String, Object> map : list) {
            String path = (String) map.get("thumbnail");
            if (StringUtils.isBlank(path)) {
                map.put("thumbnail", defPath);
            }
        }
        return AjaxResult.success("获取成功", list);
    }
}