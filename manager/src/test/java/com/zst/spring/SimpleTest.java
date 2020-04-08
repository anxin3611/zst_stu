package com.zst.spring;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 9:10
 * @description 简单的测试类
 */
public class SimpleTest {

    @Test
    public void testSystemCurrentMills() {
        // 毫秒值
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testLength() {
        int length = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTU4MjcxNjE0NiwidXNlcm5hbWUiOiJhZG1pbiJ9.aI--362GM_7_aYFJOnMUWY9Hena4w96NQ9vqm2Tp_sY".length();
        System.out.println(length);
    }

    @Test
    public void testLength1() {
        int length = "d7d09ce3865f40ed9c85d0ee5b752758".length();
        System.out.println(length);
    }

    @Test
    public void testStringFormat() {
        long l = System.currentTimeMillis() / 1000;
        System.out.println(l);
        String format = String.format("%010d", l);
        System.out.println(format);
        int i = Integer.parseInt(format);
        System.out.println(i);
        Integer integer = Integer.valueOf(format);
        System.out.println(integer);
    }

    @Test
    public void testHASH() {
        int i1 = "dfadsfsda".hashCode();
        System.out.println(i1);
        int i = "DFDFDSNFDGF".hashCode();
        System.out.println(i);
        System.out.println(File.separator);
        System.out.println("/");
    }

    @Test
    public void test() throws ParseException {
        String datetime = "2020-03-05T12:03:11.182+0000";
        String format = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parse = simpleDateFormat.parse(datetime);


        System.out.println(new Date());
        System.out.println(parse);
    }

}
