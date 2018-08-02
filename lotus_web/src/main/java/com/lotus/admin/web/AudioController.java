package com.lotus.admin.web;

import com.lotus.common.entity.AjaxResult;
import com.lotus.common.kit.StrKit;
import com.lotus.dao.pojo.Attachment;
import com.lotus.dao.pojo.Folder;
import com.lotus.rpc.service.attachment.AttachmentRpcService;
import com.lotus.rpc.service.attachment.FolderRpcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/audio")
@Controller
public class AudioController {

    @Autowired
    private AttachmentRpcService attachmentService;
    @Autowired
    private FolderRpcService folderService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "innerFlag", required = false, defaultValue = "") String innerFlag) {
        if (StrKit.notBlank(innerFlag) && "add".equals(innerFlag)) {
            // 打开弹出层需要的列表
            return "audio/_list";
        }
        return "audio/list";
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public List page(@RequestParam(value = "folderId", defaultValue = "0", required = false) Integer folderId,
                     @RequestParam(value = "audioName", required = false) String audioName) {

        String mimeType = "";

        List<Map<String, Object>> list = attachmentService.findList(mimeType, folderId, audioName);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "page/album", method = RequestMethod.POST)
    public List page(@RequestParam(value = "albumId", defaultValue = "0", required = false) Integer albumId) {
        String mimeType = "";
        if (albumId == 0) {
            return new ArrayList<Map<String, Object>>();
        }
        return attachmentService.findList(mimeType, albumId);
    }

    @ResponseBody
    @RequestMapping(value = "list/{folderId}", method = RequestMethod.POST)
    public List list(@PathVariable(value = "folderId") Integer folderId) {
        String mimeType = "";

        List<Map<String, Object>> list = attachmentService.findListByFolder(mimeType, folderId);
        return list;
    }


    @RequestMapping(value = "detail/{fileId}", method = RequestMethod.GET)
    public String detail(@PathVariable("fileId") Integer fileId, Model model) {
        Attachment attachment = attachmentService.getById(fileId);
        model.addAttribute("audio", attachment);
        return "audio/detail";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult add(@RequestParam(value = "folderId", defaultValue = "0") Integer folderId,
                          @RequestParam(value = "fileKeyAndName", required = false) String[] fileKeyAndNames) {
        if (fileKeyAndNames == null || fileKeyAndNames.length <= 0) {
            return AjaxResult.error("上传失败");
        }
        if (0 == folderId) {
            Folder rootFolder = folderService.getRootFolder();
            folderId = rootFolder.getId();
        }
        List<Attachment> list = attachmentService.add(folderId, fileKeyAndNames);

        if (list != null && list.size() > 0) {
            return AjaxResult.success("上传成功", list);
        }
        return AjaxResult.error("上传失败");
    }

    @RequestMapping(value = "/edit/{fileId}", method = RequestMethod.POST)
    public String edit(@PathVariable("fileId") Integer fileId, Model model) {
        Attachment attachment = attachmentService.getById(fileId);
        model.addAttribute("audio", attachment);
        return "audio/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(Attachment model) {
        if (model.getId() == null || model.getId() == 0) {
            return AjaxResult.error("编辑失败");
        }
        if (StringUtils.isAnyBlank(model.getTitle())) {
            return AjaxResult.error("参数错误");
        }
        boolean result = attachmentService.update(model);
        if (result) {
            return AjaxResult.success("编辑成功");
        }
        return AjaxResult.error("编辑失败");
    }

    @RequestMapping(value = "/set/thumail/{fileId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@PathVariable("fileId") Integer fileId, @RequestParam(value = "thumailPath", required = false) String thumailPath) {
        if (StringUtils.isBlank(thumailPath)) {
            return AjaxResult.error("图片不能为空");
        }
        Attachment file = attachmentService.getById(fileId);
        if (file == null) {
            return AjaxResult.error("文件不存在");
        }
        file.setThumbnail(thumailPath);
        boolean result = attachmentService.update(file);
        return result ? AjaxResult.success("设置") : AjaxResult.error("设置失败");
    }

    @RequestMapping(value = "/delete/{fileId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult delete(@PathVariable("fileId") Integer fileId) {
        boolean result = attachmentService.delete(fileId);
        if (result) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }
}
