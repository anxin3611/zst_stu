package com.zst.spring.repository;


import com.zst.spring.domain.CorporationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/10 15:13
 * @description repository
 */
@Repository
public interface CorporationRepository extends JpaRepository<CorporationDO, Short>, JpaSpecificationExecutor<CorporationDO>, Serializable {
}
