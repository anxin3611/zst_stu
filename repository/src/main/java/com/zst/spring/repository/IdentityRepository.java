package com.zst.spring.repository;


import com.zst.spring.domain.IdentityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/15 14:38
 * @description repository
 */
@Repository
public interface IdentityRepository extends JpaRepository<IdentityDO, Integer>, JpaSpecificationExecutor<IdentityDO>, Serializable {
    /**
     * 根据别名获取序列号生成规则
     *
     * @param alias
     * @return
     */
    IdentityDO findByAlias(String alias);
}
