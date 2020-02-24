package com.zst.spring.service;

import com.zst.spring.base.BaseService;
import com.zst.spring.domain.UserDO;
import com.zst.spring.exception.ZstRuntimeException;
import com.zst.spring.repository.UserRepository;
import com.zst.spring.base.BaseResponse;
import com.zst.spring.enums.ResponseCodeEnum;
import com.zst.spring.util.SecurityUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

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

    /**
     * 保存账户
     *
     * @param userDO 用户实体
     * @return com.zst.spring.base.BaseResponse<com.zst.spring.domain.UserDO>
     */
    public BaseResponse<UserDO> save(UserDO userDO) {
        String serialNum = handleSerialNum();
        UserDO save = null;
        try {
            save = userRepository.save(userDO);
        } catch (Exception e) {
            handleEx(e);
            throw new ZstRuntimeException(serialNum, ResponseCodeEnum.ERROR_2001, "user ", userDO.getUsername());
        }
        return BaseResponse.sucess(serialNum, save);
    }

    /**
     * 校验用户
     *
     * @param userDO 用户实体
     * @return com.zst.spring.base.BaseResponse<com.zst.spring.domain.UserDO>
     */
    public BaseResponse<UserDO> check(UserDO userDO) {
        String serialNum = handleSerialNum();
        if (StringUtils.isBlank(userDO.getUsername())) {
            throw new ZstRuntimeException(serialNum, ResponseCodeEnum.ERROR_3001, "username");
        }
        if (StringUtils.isBlank(userDO.getPassword())) {
            throw new ZstRuntimeException(serialNum, ResponseCodeEnum.ERROR_3001, "password");
        }
        // 根据用户名获取用户
        Optional<UserDO> one;
        try {
            one = userRepository.findOne(Example.of(new UserDO(userDO.getUsername())));
        } catch (Exception e) {
            handleEx(e);
            throw new ZstRuntimeException(serialNum, ResponseCodeEnum.ERROR_1001, "根据username");
        }
        if (!one.isPresent()) {
            throw new ZstRuntimeException(serialNum, ResponseCodeEnum.ERROR_3001, userDO.getUsername());
        }
        UserDO userStore = one.get();
        String password = userStore.getPassword();
        String salt = userStore.getSalt();
        if (StringUtils.isBlank(salt) || password.equals(userDO.getPassword())) {

        }
        if (!password.equals(SecurityUtils.generatePassword(userDO.getPassword(), salt))) {
            throw new ZstRuntimeException(serialNum, ResponseCodeEnum.ERROR_4001);
        }
        return BaseResponse.sucess(serialNum, userStore);
    }

}
