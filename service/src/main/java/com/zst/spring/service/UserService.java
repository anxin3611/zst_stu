package com.zst.spring.service;

import com.zst.spring.base.BaseService;
import com.zst.spring.domain.UserDO;
import com.zst.spring.enums.IdentityEnums;
import com.zst.spring.exception.ZstRuntimeException;
import com.zst.spring.repository.UserRepository;
import com.zst.spring.base.BaseResponse;
import com.zst.spring.enums.ResponseCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 13:48
 * @description 用户相关Service
 */
@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends BaseService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private IdentityService identityService;

    public BaseResponse<UserDO> save(UserDO userDO) {
        UserDO save = null;
        try {
            save = userRepository.save(userDO);
        } catch (Exception e) {
            handleEx(e);
            throw new ZstRuntimeException(ResponseCodeEnum.ERROR_2001, "user ", userDO.getUsername());
        }
        return BaseResponse.SUCCESS(identityService.serialNum(IdentityEnums.AliasEnums.SERIAL_NUM), save);
    }

}
