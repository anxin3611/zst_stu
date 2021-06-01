package com.zst.spring.repository;


import com.zst.spring.domain.UserDO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/10 15:13
 * @description repository，用户相关
 */
//@Repository
public interface UserRepository extends CrudRepository<UserDO, Short> {
}
