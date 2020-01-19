package com.zst.spring.repository;


import com.zst.spring.domain.IdentityDO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/15 14:38
 * @description repository，序列号生成相关
 */
@Repository
public interface IdentityRepository extends BaseRepository<IdentityDO, Integer> {
    /**
     * 根据别名获取序列号规则
     *
     * @param alias 别名
     * @return java.util.Optional<com.zst.spring.domain.IdentityDO>
     */
    Optional<IdentityDO> findByAlias(String alias);

}
