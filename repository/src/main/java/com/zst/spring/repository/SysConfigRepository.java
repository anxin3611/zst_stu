package com.zst.spring.repository;


import com.zst.spring.domain.SysConfigDO;
import org.springframework.stereotype.Repository;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/10 15:13
 * @description repository，系统配置表 sys_config
 */
@Repository
public interface SysConfigRepository extends BaseRepository<SysConfigDO, Long> {
}
