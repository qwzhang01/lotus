package com.lotus.admin.web;

import com.lotus.rpc.service.attachment.AttachmentRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/image")
@Controller
public class ImageController {
    @Autowired
    private AttachmentRpcService attachmentService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "image/list";
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public List page(@RequestParam(value = "folderId", defaultValue = "0", required = false) Integer folderId,
                     @RequestParam(value = "audioName", required = false) String audioName) {

        String mimeType = "";

        List<Map<String, Object>> list = attachmentService.findList(mimeType, folderId, audioName);
        return list;
    }
}
