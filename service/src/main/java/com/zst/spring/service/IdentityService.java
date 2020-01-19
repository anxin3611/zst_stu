package com.zst.spring.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.zst.spring.domain.IdentityDO;
import com.zst.spring.enums.IdentityEnums;
import com.zst.spring.exception.ZstRuntimeException;
import com.zst.spring.repository.IdentityRepository;
import com.zst.spring.util.enums.ResponseCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/16 11:39
 * @description 序列号生成
 */
@Service
public class IdentityService {
    @Resource
    private IdentityRepository identityRepository;

    /**
     * 根据别名获取序列号生成规则
     *
     * @param aliasEnum 别名的枚举
     * @return java.lang.String
     */
    public String serialNum(IdentityEnums.AliasEnums aliasEnum) {
        // 默认UUID
        StringBuilder result = new StringBuilder().append(IdUtil.randomUUID());
        Optional<IdentityDO> aliasOpt = identityRepository.findByAlias(aliasEnum.alias);
        if (!aliasOpt.isPresent()) {
            throw new ZstRuntimeException(ResponseCodeEnum.ERROR_1001, null, "规则不存在，serialNum");
        }
        IdentityDO byAlias = aliasOpt.get();
        // 生成类型是每日生成
        if (IdentityEnums.GetTypeEnums.UUID.code == byAlias.getType()) {
            String rule = byAlias.getRule();
            // 年月日时分秒都有 {yyyy}{MM}{DD}{HH}{II}{SS}{NO}
            if (StringUtils.isNotBlank(rule) && rule.contains(IdentityEnums.DateTypeEnums.FULL_DATE.dateType)) {
                String dateValue = DateUtil.format(new Date(), "yyyyMMddHHmmss");
                String no = IdUtil.simpleUUID();
                result = new StringBuilder(dateValue).append("-").append(no);
            }
        }
        return result.toString();
    }
}
