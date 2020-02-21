/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：weiwei
 * @version ：V1.0
 * @program ：vevor-bmp
 * @date ：Created in 2020/1/24 12:49 下午
 * @description ：Date time utils
 */
public class DateTimeUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date currentTime;
    private long threadId;
    private String threadStackTraceStr;

    public DateTimeUtil() {
        currentTime = new Date();
        Thread currentT = Thread.currentThread();
        StackTraceElement[] stackTraceElements = currentT.getStackTrace();
        if (NPTUtil.isArrayUsable(stackTraceElements)) {
            threadStackTraceStr = stackTraceElements[stackTraceElements.length - 1].toString();
        }
        threadId = Thread.currentThread().getId();
    }

    public String debugString() {
        return threadStackTraceStr + "[ThreadID=" + threadId + "]: " + simpleDateFormat.format(currentTime);
    }

    public String intervalString() {
        Date newDate = new Date();
        return threadStackTraceStr + "[ThreadID=" + threadId + "]: " + simpleDateFormat.format(newDate) +
                " Interval[" + (newDate.getTime() - currentTime.getTime()) + "ms]";
    }

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);
        //
        SimpleDateFormat sdf = new SimpleDateFormat();
        System.out.println(sdf.format(d));
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cc = sdf1.format(d);
        System.out.println(cc);

        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        System.out.println(dateTimeUtil.debugString());
        System.out.println(dateTimeUtil.intervalString());
    }
}
