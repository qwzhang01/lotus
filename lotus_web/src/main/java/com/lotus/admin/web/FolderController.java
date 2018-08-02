package com.lotus.admin.web;

import com.lotus.common.entity.AjaxResult;
import com.lotus.dao.pojo.Folder;
import com.lotus.rpc.service.attachment.FolderRpcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理文件夹控制器
 */
@RequestMapping("/folder")
@Controller
public class FolderController {

    @Autowired
    private FolderRpcService folderService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public List<Folder> getAll() {
        return folderService.findAll();
    }


    @RequestMapping(value = "/add/{pFolderId}", method = RequestMethod.POST)
    public String add(@PathVariable("pFolderId") Integer pFolderId, Model model) {
        model.addAttribute("pFolderId", pFolderId);
        return "folder/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(@RequestParam(value = "pFolderId", defaultValue = "0", required = false) Integer pFolderId,
                           @RequestParam("name") String name) {
        if (StringUtils.isBlank(name)) {
            return AjaxResult.error("文件夹名字为空");
        }
        boolean result = folderService.add(pFolderId, name);
        if (result) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @RequestMapping(value = "/edit/{folderId}", method = RequestMethod.POST)
    public String edit(@PathVariable("folderId") Integer folderId, Model model) {
        Folder folder = folderService.getById(folderId);
        model.addAttribute("folder", folder);
        return "folder/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestParam("folderId") Integer folderId,
                             @RequestParam("name") String name) {
        Folder folder = folderService.getById(folderId);
        if (folder == null) {
            return AjaxResult.error("文件夹不存在");
        }
        if (StringUtils.isBlank(name)) {
            return AjaxResult.error("文件夹名字为空");
        }
        folder.setName(name);
        boolean result = folderService.update(folder);
        if (result) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    @RequestMapping(value = "/delete/{folderId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult delete(@PathVariable("folderId") Integer folderId) {
        boolean result = folderService.delete(folderId);
        if (result) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }
}