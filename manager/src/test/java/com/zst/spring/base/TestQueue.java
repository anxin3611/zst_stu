package com.zst.spring.base;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/19 12:41
 * @description ：
 */
public class TestQueue implements Delayed {
    @Override
    public long getDelay(@NotNull TimeUnit timeUnit) {
        return 0;
    }

    @Override
    public int compareTo(@NotNull Delayed delayed) {
        return 0;
    }
}
