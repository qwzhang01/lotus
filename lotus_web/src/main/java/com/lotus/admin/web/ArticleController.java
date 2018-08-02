package com.lotus.admin.web;

import com.lotus.common.entity.AjaxResult;
import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Content;
import com.lotus.rpc.service.content.ContentRpcService;
import com.lotus.rpc.service.content.TaxonomyRpcService;
import com.lotus.rpc.service.system.OptionRpcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

@RequestMapping("/article")
@Controller
public class ArticleController {

    private static final String FLAG = "article";

    @Resource
    private OptionRpcService optionService;
    @Resource
    private ContentRpcService contentService;
    @Resource
    private TaxonomyRpcService taxonomyService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("column", taxonomyService.findByAppColumn(FLAG));
        return "article/list";
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
    public String detail(@RequestParam(value = "articleId", required = false) Integer articleId,
                         Model model) throws UnsupportedEncodingException {
        String h5_href = optionService.get("h5_href") + "/article/" + articleId;
        model.addAttribute("h5_href", h5_href);
        model.addAttribute("h5_href_param", URLEncoder.encode(h5_href, "utf-8"));
        return "article/detail";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "articleId", required = false) Integer albumId,
                       Model model) {
        model.addAttribute("column", taxonomyService.findByAppColumn(FLAG));
        Content content = contentService.getById(albumId);
        model.addAttribute("content", content);
        return "article/form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Content content, RedirectAttributes redirectAttributes) {
        if (StringUtils.isAnyBlank(content.getTitle(), content.getSource())) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("参数错误"));
            return "redirect:/article/form";
        }
        if (content.getTaxonomyId() == null || content.getTaxonomyId() == 0) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("参数错误"));
            return "redirect:/article/form";
        }
        Boolean result = false;
        //保存
        if (content.getId() != null && content.getId() != 0) {
            result = contentService.update(content);
        } else {
            result = contentService.add(content);
        }

        if (result) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("保存成功"));
        } else {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("保存失败"));
        }
        return "redirect:/article/list";
    }

    @ResponseBody
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    public AjaxResult publish(@RequestParam("articleId") Integer articleId) {
        Content content = contentService.getById(articleId);
        if (content == null) {
            return AjaxResult.error("发布失败，文章不存在");
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
    public AjaxResult sticky(@RequestParam("articleId") Integer articleId) {
        Content content = contentService.getById(articleId);
        if (content == null) {
            return AjaxResult.error("操作失败，文章不存在");
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

    @RequestMapping(value = "move/{move}/{articleId}", method = RequestMethod.GET)
    public String move(@PathVariable("move") String move, @PathVariable("articleId") Integer articleId, RedirectAttributes redirectAttribute) {
        boolean result = contentService.reSort(articleId, "up".equals(move));//up   down
        if (result) {
            redirectAttribute.addFlashAttribute("redirectMsg", AjaxResult.success("移动成功"));
        } else {
            redirectAttribute.addFlashAttribute("redirectMsg", AjaxResult.error("移动失败"));
        }
        return "redirect:/article/list";
    }

    @RequestMapping(value = "delete/{articleId}", method = RequestMethod.GET)
    public String delete(@PathVariable("articleId") Integer articleId, RedirectAttributes redirectAttributes) {
        Content content = contentService.getById(articleId);
        if (content == null) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("删除失败"));
            return "redirect:/article/list";
        }
        Boolean delete = contentService.delete(content);
        if (delete) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("删除成功"));
        } else {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("删除失败"));
        }
        return "redirect:/article/list";
    }
}