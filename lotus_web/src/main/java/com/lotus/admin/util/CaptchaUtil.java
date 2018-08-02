package com.lotus.admin.util;

import com.lotus.admin.shiro.ShiroCrypt;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author zqw
 */
public class CaptchaUtil {
    /**
     * 随机字符字典
     */
    private static final char[] CHARS = {'2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    /**
     * 随机数
     */
    private static Random random = new Random();

    private CaptchaUtil() {
    }

    /**
     * 获取6位随机数
     */
    private static String getRandomString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    public static void outputCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");

        String randomString = getRandomString();
        request.getSession(true).setAttribute("captcha", ShiroCrypt.md5(randomString.toUpperCase()));

        BufferedImage bi = new BufferedImage(100, 31, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 100, 31);
        g.setColor(Color.BLACK);

        for(int i = 0; i < randomString.length(); i++){
            g.drawString(randomString.substring(i, i + 1), 7 + 16 * (i + 1), 20);
        }

        // 转成JPEG格式
        ServletOutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bi);
        out.flush();
    }

    public static boolean validateCaptcha(String captcha, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionAttribute = (String) session.getAttribute("captcha");
        session.removeAttribute("captcha");
        if (StringUtils.isBlank(sessionAttribute)) {
            return false;
        }
        String md5Captcha = ShiroCrypt.md5(captcha.toUpperCase());
        return md5Captcha.equals(sessionAttribute);
    }
}
