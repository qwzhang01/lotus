package com.lotus.admin.web;

import com.google.zxing.WriterException;
import com.lotus.admin.util.QrCodeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * 二维码控制器
 */
@RequestMapping("/qrcode")
@Controller
public class QrCodeController {

    @RequestMapping("/gen")
    public ResponseEntity<byte[]> genQrCode(@RequestParam("url")String url)
            throws WriterException, IOException {
        url = URLDecoder.decode(url, "utf-8");
        return QrCodeUtil.getResponseEntity(url, 150, 150, "png");
    }
}