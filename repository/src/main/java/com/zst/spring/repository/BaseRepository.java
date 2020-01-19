package com.zst.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 13:07
 * @description 基础Repository
 */
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, PagingAndSortingRepository<T, ID>, Serializable {
    /**
     * 批量插入
     *
     * @param var1 要插入的数据
     * @return java.lang.Iterable<S>
     * @date 2020/1/16 20:16
     */
    <S extends T> Iterable<S> batchSave(Iterable<S> var1);

    /**
     * 批量更新
     *
     * @param var1 要插入的数据
     * @return java.lang.Iterable<S>
     * @date 2020/1/16 20:16
     */
    <S extends T> Iterable<S> batchUpdate(Iterable<S> var1);
}
