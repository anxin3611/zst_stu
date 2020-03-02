package com.zst.spring.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zst.spring.ZstApplication;
import com.zst.spring.enums.IdentityEnums;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 9:26
 * @description
 */
@SpringBootTest(classes = ZstApplication.class)
public class IdentityServiceTest {
    @Resource
    private IdentityService identityService;

    @Test
    public void serialNum() {
        String serialNum = identityService.serialNum(IdentityEnums.AliasEnums.SERIAL_NUM);
    }

    private void printSerialNum() {
        int i = Long.hashCode(Thread.currentThread().getId());
        System.out.println(i);
    }

    @Test
    public void theadSerialNum() {
// new ArrayBlockingQueue<>(512),使用有界队列，避免OOM（线程耗尽）
        ExecutorService executorService = new ThreadPoolExecutor(100, 100, 3000, TimeUnit.SECONDS,
                new PriorityBlockingQueue<>(100),
                new ThreadFactoryBuilder().build());

        executorService.execute(this::printSerialNum);
        executorService.execute(this::printSerialNum);

    }
}