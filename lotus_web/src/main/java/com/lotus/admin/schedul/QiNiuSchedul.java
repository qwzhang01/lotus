package com.lotus.admin.schedul;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class QiNiuSchedul {
    private static int counter = 0;

    @Scheduled(cron = "0/5 * * * * ?")
    protected void execute() {
        //定时删除云中多余的文件
        // long ms = System.currentTimeMillis();
        // System.out.print("(" + counter++ + ")");
        // System.out.println("\t\t" + new Date(ms));
    }
}
