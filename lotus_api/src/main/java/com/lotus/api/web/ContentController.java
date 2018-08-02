package com.lotus.api.web;

import com.lotus.api.entity.LotusResult;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.HttpKit;
import com.lotus.common.kit.JsonKit;
import com.lotus.dao.pojo.Content;
import com.lotus.rpc.service.attachment.AttachmentRpcService;
import com.lotus.rpc.service.content.AudioAttachmentRpcService;
import com.lotus.rpc.service.content.ContentRpcService;
import com.lotus.rpc.service.system.OptionRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 需求简单汇总
 * <p>
 * APP显示：
 * 取药获取文章列表
 * 文章详情——h5
 * 专辑列表——原生
 * 专辑详情——原生
 * <p>
 * API设计
 * <p>
 * 分享页面：
 * 文章详情分享页
 * 专辑列表分享页
 * 单个音频分享页
 */
@Api(tags = "内容管理", description = "获取文章、专辑、曲目")
@RequestMapping("/api")
@Controller
public class ContentController {
    //swagger2 注解说明
    //http://www.jianshu.com/p/12f4394462d5
    @Autowired
    private OptionRpcService optionService;
    @Autowired
    private ContentRpcService contentService;
    @Autowired
    private AudioAttachmentRpcService audioAttachmentService;
    @Autowired
    private AttachmentRpcService attachmentService;

    /**
     * 获取内容列表 包括文章列表、专辑列表
     *
     * @param appColumn  app栏目
     * @param sticky     0获取不置顶 1获取置顶内容
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取文章、专辑列表", httpMethod = "POST", response = LotusResult.class, notes = "根据栏目，获取对应的文章或专辑分页列表")
    @RequestMapping(value = "/list/{appColumn}", method = RequestMethod.POST)
    @ResponseBody
    public LotusResult list(@PathVariable("appColumn") String appColumn,
                            @RequestParam(value = "sticky", required = false) Integer sticky,
                            @RequestParam(value = "pageNumber", required = false, defaultValue = "1")Integer pageNumber,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize,
                            HttpServletRequest request) {

        //转化请求参数
        try{
            String readData = HttpKit.readData(request);
            if(StringUtils.isNoneBlank(readData)){
                Map<String, Object> parasMap = JsonKit.toMap(readData);
                if(parasMap != null && parasMap.size() > 0){
                    if(parasMap.containsKey("sticky")){
                        sticky = Integer.parseInt(parasMap.get("sticky").toString());
                    }
                    if(parasMap.containsKey("pageNumber")){
                        pageNumber = Integer.parseInt(parasMap.get("pageNumber").toString());
                    }
                    if(parasMap.containsKey("pageSize")){
                        pageSize = Integer.parseInt(parasMap.get("pageSize").toString());
                    }
                }
            }
        }catch (Exception e){
            return LotusResult.error("参数格式错误");
        }

        //处理特殊情况
        if (-1 == pageNumber) {
            pageNumber = 1;
            pageSize = Integer.MAX_VALUE;
        }
        //正式查询
        Page<Map<String, Object>> page = contentService.findPageList(appColumn, sticky, pageNumber, pageSize);
        List<Map<String, Object>> list = page.getList();
        for (Map<String, Object> map : list) {
            String thumbnail = (String) map.get("thumbnail");
            if (StringUtils.isNoneBlank(thumbnail)) {//补全图片路径
                map.put("thumbnail", optionService.get("qiniu_path") + "/" + thumbnail);
            } else {
                map.put("thumbnail", optionService.get("qiniu_path") + "/" + optionService.get("default_thumbnail"));
            }
            String icon = (String) map.get("icon");
            if (!StringUtils.isBlank(icon)) {//补全图片路径
                map.put("icon", optionService.get("qiniu_path") + "/" + icon);
            } else {
                map.put("icon", map.get("thumbnail"));
            }
            String type = (String) map.get("type");
            if ("article".equals(type)) {
                map.put("h5Href", optionService.get("h5_href") + "/article/" + map.get("contentId"));
            } else if ("audio".equals(type)) {
                map.put("h5Href", optionService.get("h5_href") + "/album/" + map.get("contentId"));
            } else {
                map.put("h5Href", "");
            }

        }
        return LotusResult.success(page);
    }

    /**
     * 获取一个音乐的专辑的所有音乐
     *
     * @param contentId  音乐专辑ID（内容ID）
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取曲目列表", httpMethod = "POST", response = LotusResult.class, notes = "根据专辑ID获取曲目分页列表")
    @RequestMapping(value = "/audio/{contentId}", method = RequestMethod.POST)
    @ResponseBody
    public LotusResult audio(@PathVariable("contentId") Integer contentId,
                             @RequestParam(value = "pageNumber", required = false, defaultValue = "1")Integer pageNumber,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize,
                             HttpServletRequest request) {

        //转化请求参数
        try{
            String readData = HttpKit.readData(request);
            if(StringUtils.isNoneBlank(readData)){
                Map<String, Object> parasMap = JsonKit.toMap(readData);
                if(parasMap != null && parasMap.size() > 0){
                    if(parasMap.containsKey("pageNumber")){
                        pageNumber = Integer.parseInt(parasMap.get("pageNumber").toString());
                    }
                    if(parasMap.containsKey("pageSize")){
                        pageSize = Integer.parseInt(parasMap.get("pageSize").toString());
                    }
                }
            }
        }catch (Exception e){
            return LotusResult.error("参数格式错误");
        }

        if (-1 == pageNumber) {
            pageNumber = 1;
            pageSize = Integer.MAX_VALUE;
        }

        Page<Map<String, Object>> page = audioAttachmentService.findPageList(contentId, pageNumber, pageSize);
        List<Map<String, Object>> list = page.getList();
        for (Map<String, Object> map : list) {
            String thumbnail = (String) map.get("thumbnail");
            if (StringUtils.isNoneBlank(thumbnail)) {//补全图片路径
                map.put("thumbnail", optionService.get("qiniu_path") + "/" + thumbnail);
            } else {
                map.put("thumbnail", optionService.get("qiniu_path") + "/" + optionService.get("default_thumbnail"));
            }
            String audioPath = (String) map.get("audioPath");
            if (StringUtils.isNoneBlank(audioPath)) {//补全音频路径
                map.put("audioPath", optionService.get("qiniu_path") + "/" + audioPath);
            }
            map.put("h5Href", optionService.get("h5_href") + "/audio/" + map.get("audioId"));
        }
        return LotusResult.success(page);
    }

    /**
     * 获取一个音乐详情
     *
     * @param audioId 音乐ID
     * @return
     */
    @ApiOperation(value = "获取曲目详情", httpMethod = "POST", response = LotusResult.class, notes = "根据曲目ID获取曲目详情")
    @RequestMapping(value = "/audio/detail/{audioId}", method = RequestMethod.POST)
    @ResponseBody
    public LotusResult audioDetail(@PathVariable("audioId") Integer audioId) {
        if (audioId == null) {
            return LotusResult.error("音频ID不能为空");
        }
        Map<String, Object> detail = audioAttachmentService.getAudioDetail(audioId);
        if (detail != null) {
            String thumbnail = (String) detail.get("thumbnail");
            if (StringUtils.isNoneBlank(thumbnail)) {//补全图片路径
                detail.put("thumbnail", optionService.get("qiniu_path") + "/" + thumbnail);
            } else {
                detail.put("thumbnail", optionService.get("qiniu_path") + "/" + optionService.get("default_thumbnail"));
            }
            String audioPath = (String) detail.get("audioPath");
            if (StringUtils.isNoneBlank(audioPath)) {//补全音频路径
                detail.put("audioPath", optionService.get("qiniu_path") + "/" + audioPath);
            }
        }
        return LotusResult.success(detail);
    }

    @ApiOperation(value = "获取文章详情", httpMethod = "POST", response = LotusResult.class, notes = "根据文章ID获取文章详情")
    @RequestMapping(value = "/article/detail/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public LotusResult articleDetail(@PathVariable("articleId") Integer articleId){
        if (articleId == null) {
            return LotusResult.error("音频ID不能为空");
        }
        Content detail = contentService.getById(articleId);
        if (detail != null) {
            String thumbnail = detail.getThumbnail();
            if (StringUtils.isNoneBlank(thumbnail)) {
                detail.setThumbnail(optionService.get("qiniu_path") + "/" + thumbnail);
            }
        }
        return LotusResult.success(detail);
    }
}