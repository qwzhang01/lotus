package com.lotus.admin.web;

import com.lotus.common.entity.AjaxResult;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.StrKit;
import com.lotus.dao.pojo.Content;
import com.lotus.rpc.service.content.ContentRpcService;
import com.lotus.rpc.service.content.TaxonomyRpcService;
import com.lotus.rpc.service.system.OptionRpcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

@RequestMapping("/album")
@Controller
public class AlbumController {
    private static final String FLAG = "audio";

    @Resource
    private OptionRpcService optionService;
    @Resource
    private ContentRpcService contentService;
    @Resource
    private TaxonomyRpcService taxonomyService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("column", taxonomyService.findByAppColumn(FLAG));
        return "album/list";
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Page page(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                     @RequestParam(value = "tId", required = false) Integer tId,
                     @RequestParam(value = "title", required = false) String title,
                     @RequestParam(value = "begin", required = false) Date begin,
                     @RequestParam(value = "end", required = false) Date end
    ) {

        Page<Map<String, Object>> list = contentService.findList(FLAG, tId, title, begin, end, pageNumber, pageSize);
        return list;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "albumId", required = false) Integer albumId,
                       Model model) throws UnsupportedEncodingException {
        String h5_href = optionService.get("h5_href") + "/album/" + albumId;
        model.addAttribute("h5_href", h5_href);
        model.addAttribute("h5_href_param", URLEncoder.encode(h5_href, "utf-8"));
        return "album/detail";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "albumId", required = false) Integer albumId,
                       Model model) {
        model.addAttribute("column", taxonomyService.findByAppColumn(FLAG));
        Content content = contentService.getById(albumId);
        model.addAttribute("content", content);
        return "album/form";
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public AjaxResult save(@RequestParam(value = "audioId[]", required = false) Integer[] audioId, Content content) {
        if (StrKit.isBlank(content.getTitle())) {
            return AjaxResult.error("标题不能为空");
        }
        if (StrKit.isBlank(content.getSource())) {
            return AjaxResult.error("来源不能为空");
        }
        if (content.getTaxonomyId() == null || content.getTaxonomyId() == 0) {
            return AjaxResult.error("栏目不能为空");
        }
        if (audioId == null || audioId.length <= 0) {
            //return AjaxResult.error("音频不能为空");
        }
        Boolean result = false;
        //保存
        if (content.getId() != null && content.getId() != 0) {
            result = contentService.update(content, audioId);
        } else {
            result = contentService.add(content, audioId);
        }

        if (result) {
            return AjaxResult.success("保存成功");
        } else {
            return AjaxResult.error("保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    public AjaxResult publish(@RequestParam("albumId") Integer albumId) {
        Content content = contentService.getById(albumId);
        if (content == null) {
            return AjaxResult.error("发布失败，专辑不存在");
        }
        String msg = "";
        Integer isPublished = content.getIsPublished();//发布状态:0未发布;1已发布
        if (1 == isPublished) {
            content.setIsPublished(0);
            msg = "撤销";
        } else {
            content.setIsPublished(1);
            msg = "发布";
        }
        Boolean result = contentService.update(content);
        if (result) {
            return AjaxResult.success(msg + "成功");
        }
        return AjaxResult.error(msg + "失败");
    }

    @ResponseBody
    @RequestMapping(value = "sticky", method = RequestMethod.POST)
    public AjaxResult sticky(@RequestParam("albumId") Integer albumId) {
        Content content = contentService.getById(albumId);
        if (content == null) {
            return AjaxResult.error("操作失败，专辑不存在");
        }
        String msg = "";
        Integer isStick = content.getIsStick();//置顶状态:0不置顶;1置顶
        if (1 == isStick) {
            content.setIsStick(0);
            msg = "撤销";
        } else {
            content.setIsStick(1);
            msg = "置顶";
        }
        Boolean result = contentService.update(content);
        if (result) {
            return AjaxResult.success(msg + "成功");
        }
        return AjaxResult.error(msg + "失败");
    }

    @ResponseBody
    @RequestMapping(value = "move/{move}/{albumId}", method = RequestMethod.POST)
    public AjaxResult move(@PathVariable("move") String move, @PathVariable("albumId") Integer albumId) {
        boolean result = contentService.reSort(albumId, "up".equals(move));//up   down
        if (result) {
            return AjaxResult.success("移动成功");
        } else {
            return AjaxResult.success("移动失败");
        }
    }

    @RequestMapping(value = "delete/{albumId}", method = RequestMethod.GET)
    public String delete(@PathVariable("albumId") Integer albumId, RedirectAttributes redirectAttributes) {
        Content content = contentService.getById(albumId);
        if (content == null) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("删除失败"));
            return "redirect:/album/list";
        }
        Boolean delete = contentService.delete(content);
        if (delete) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("删除成功"));
        } else {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("删除失败"));
        }
        return "redirect:/album/list";
    }
}