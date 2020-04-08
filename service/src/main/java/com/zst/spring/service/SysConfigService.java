package com.zst.spring.service;

import com.alibaba.fastjson.JSON;
import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.*;
import com.zst.spring.base.ErrorMessage;
import com.zst.spring.domain.SysConfigDO;
import com.zst.spring.enums.AuthTypeEnum;
import com.zst.spring.enums.TokenTypeEnum;
import com.zst.spring.repository.SysConfigRepository;
import com.zst.spring.util.EbayUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/23 13:25
 * @description ：表 sys_config 的service
 */
@Service
@Slf4j
public class SysConfigService {
    @Resource
    private SysConfigRepository sysConfigRepository;
    @Resource
    private OAuth2Api oAuth2Api;


    /**
     * 客户凭证授予类型，获取不同环境下应用程序访问令牌，
     *
     * @return 应用程序访问令牌，已调通，可以访问仅需要应用程序令牌即可授权的ebay方法，OAuth Scopes：【Client Credential Grant Type】
     */
    public String getApplicationToken() {
        // 返回值，token
        List<String> scopes = EbayUtils.scopes("SINGLE");
        try {
            // 使用ebay sdk调取ebay rest接口
            OAuthResponse oAuthResponse = oAuth2Api.getApplicationToken(Environment.SANDBOX, scopes);
            String token = getString(oAuthResponse, TokenTypeEnum.TOKEN, AuthTypeEnum.APPLICATION);

            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    /**
     * 授权码凭证，获取授权URL
     *
     * @param shopName shopName
     * @return 返回授权的URL
     */
    public String authRestfulUrl(String shopName) {
        // ebay 授权链接
        List<String> scopeList = EbayUtils.scopes("");
        String authorizationUrl = oAuth2Api.generateUserAuthorizationUrl(Environment.SANDBOX, scopeList, Optional.empty());
        // Optional.of(shopName) 是回调时是否带上参数
        // 保存authrizationUrl
        SysConfigDO sysConfigDO = new SysConfigDO();
        sysConfigDO.setAuthUrl(authorizationUrl);
        sysConfigDO.setExpireTime(new Date());
        sysConfigDO.setFreshExpireTime(new Date());
        sysConfigDO.setCreateTime(new Date());
        sysConfigDO.setUpdateTime(new Date());
        sysConfigDO.setInUse(true);
        sysConfigDO.setConfigName("ebay测试沙箱");
        sysConfigDO.setAppId("jianli-thisisli-SBX-c69ec6b8e-18115ce9");
        sysConfigDO.setCertId("SBX-69ec6b8e490c-0756-42ca-922b-4750");
        sysConfigDO.setDevId("ab1132f7-a732-464b-8677-04025943aa25");
        sysConfigDO.setRedirectUri("jian_li-jianli-thisisli-khphh");
        SysConfigDO save = sysConfigRepository.save(sysConfigDO);
        return authorizationUrl;
    }

    /**
     * 授权码凭证，根据code获取token
     * v^1.1#i^1#f^0#p^3#r^0#I^3#t^H4sIAAAAAAAAAOVXe2wURRjvXR9CystIeQXMuZCIkr2b3b3Xbrgj1/ZIj0J79EpDESSzu7Pttnu7584c7aEmpSjhYSIggon+UTVCExJDICZq1ChRqEl9hKgRoxhBA4Y/MGoEJVpnrw+uNQJSNE28u2RvZr755vv9vt/M7Ae6yibfv61m2+WprjvcPV2gy+1yceVgclnpkmnF7nmlRaDAwNXTtairpLv4wlIM00ZGakA4Y5kYeTrThomlfGeEydqmZEGsY8mEaYQlokip2KqVEu8FUsa2iKVYBuNJVEeYcED1B2VFRIgLCOEwoL3msM9GK8JwCpAV6PcrsuKHmibQcYyzKGFiAk0SYXjAAxYILC80AkGiP4H38mJoHeNpQjbWLZOaeAETzYcr5efaBbFeP1SIMbIJdcJEE7HlqfpYojpe17jUV+ArOsRDikCSxaNbVZaKPE3QyKLrL4Pz1lIqqygIY8YXHVxhtFMpNhzMLYSfpzoo85qgiRwQA5TTwG1hcrllpyG5fhhOj66yWt5UQibRSe5GhFIy5DakkKFWHXWRqPY4j9VZaOiajuwIE6+MNa9JxRsYTyqZtK1NuopUBygn+AU+RNXERAnClEFkb9yMCeA5YWipQX9DPI9Zq8oyVd1hDXvqLFKJaNxoNDu8FChghxrVm/V2TCNOTIV2gREWhXVOVgfTmCWtppNYlKZUePLNG+dgWBPXVHC7VCEGBSEMeBUFYUAUEDdWFs5evxVpRJ3sxJJJnxMLkmGOTUO7HZGMARXEKpTebBrZuioJAY0Xwhpi1aCosX5R01g5oAZZTkMIICTTwyH8/1IIIbYuZwkaUcnYgTzMCOOwKulQk4jVjszGXAYxYy3zp8+QNDpxhGklJCP5fB0dHd4OwWvZLT4eAM63dtXKlNKK0pAZsdVvbMzqeZEoiM7CukRoABGmk2qQLm62MNGG+PKGeKpmY2N9bbxuWL+jIouO7f0bpCmk2IhMLHTtTemHVAPFUHPLirpqlGve3LhiidGW9vuqlGxbPAON0IoaX20wVoMj4wOvWBmUtAxdyf3LDDh7/R+yINhqEtokV5nN0XYKGQZ9jAsuduBOrFQ78zF1ADO619l0XsVK+yxIj26na2M+Ys/NGPkwJcg7eBBSz7cyx2sjqFqmkRsXybFMJpFOZwmUDZRQJxbbgiCGBTBueBMMVZsOTUNnSatOr2X6J1W5llWCIlKCchixXJjjAgoSx4W6Gm26edQl3a6+/wY5lDlO4LUQC0MCz/qDfpkNB0MhFvgBHxD9AoR8YFy4qwydXpIT7/6rsTBB6s1CG9NRcPn/5c3PN7r2ihblP1y3603Q7XqNlm8gBFhuCbivrHhNSfEUBusEeTE0Vdnq9NIXBi/WW0xaWtjI245yGajb7jKX/sUnypWCqq9nA5gzUvdNLubKC4pAMP/aSCk3ffZUSozAC4B+BX4dWHhttISbVTLzxcSM1JHcC+z+Gb3xgc6rlz/bt+cYmDpi5HKVFlE5Fq0dOPTB+8fDB7/a5T+nfT+Ff2fgrvCJnN727s4Pv82Ezv8ovl2zbOtv5xc+Yp3qfsw959yuP7oGtP31/mnuHb1b2pMVc+956vQ3611tRxp+XllbkVp8uufAD1veuzRz54WXZgmnztceLI09c/zsFHv+zs+Pir1bj34kXe17a9+9T3899+D0lxeH7uYj3K7nK06+0d+3wf/pRffhykPuO/ev2uNa1rN70i9HPj6wGD/w3OoF7ie0HQsarpwov7Q7O11NDpwsKZ+9e/2Ty/x1/ZO2y2fL9s7ufz39bLZcJmf2Xnql5tGL1Y//ZFn8tipUUfm7/d2Dh61jzV8yW87U5nrFPk3s6391w6KmXzPztj88mMY/AcHr2BePDwAA
     *
     * @param code
     * @return
     * @throws IOException
     */
    public String getTokenByCode(String code) throws IOException {
        OAuthResponse oAuthResponse = oAuth2Api.exchangeCodeForAccessToken(Environment.SANDBOX, code);
        String token = getString(oAuthResponse, TokenTypeEnum.TOKEN, AuthTypeEnum.USER);

        return token;
    }

    /**
     * 根据refreshToken 获取token
     *
     * @param freshToken freshToken
     * @return token
     */
    public String reRefreshToken(String freshToken) {
        // ebay 授权链接
        List<String> scopeList = EbayUtils.scopes("");
        try {
            OAuthResponse oAuthResponse = oAuth2Api.getAccessToken(Environment.SANDBOX, freshToken, scopeList);

            return getString(oAuthResponse, TokenTypeEnum.REFRESH_TOKEN, AuthTypeEnum.USER);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理ebay auth接口的返回参数
     *
     * @param oAuthResponse ebay auth 接口的返回参数
     * @return token
     */
    private String getString(OAuthResponse oAuthResponse, TokenTypeEnum tokenType, AuthTypeEnum authType) {
        Optional<SysConfigDO> one = sysConfigRepository.findById(5L);
        String token = null;

        if (one.isPresent()) {
            SysConfigDO sysConfigDO = one.get();

            // 请求报错的情况
            if (StringUtils.isNotBlank(oAuthResponse.getErrorMessage())) {
                ErrorMessage errorMessage = JSON.parseObject(oAuthResponse.getErrorMessage(), ErrorMessage.class);
                log.info(String.valueOf(errorMessage));
                return null;
            }
            // 获取token相关信息
            if (oAuthResponse.getAccessToken().isPresent()) {
                AccessToken accessToken = oAuthResponse.getAccessToken().get();
                token = accessToken.getToken();
                log.info("accessToken is ===> {}", token);
                Date accessTokenDate = accessToken.getExpiresOn();
                log.info("accessTokenDate is ===> {}", accessTokenDate);
                TokenType accessTokenType = accessToken.getTokenType();
                log.info("accessTokenType is ===> {}", accessTokenType);
                sysConfigDO.setToken(token);
                sysConfigDO.setExpireTime(accessTokenDate);
                sysConfigDO.setUpdateTime(new Date());
                sysConfigDO.setConfigType(tokenType.name());
            }
            // 获取refreshToken相关信息
            if (TokenTypeEnum.REFRESH_TOKEN.compareTo(tokenType) != 0) {
                if (oAuthResponse.getRefreshToken().isPresent()) {
                    RefreshToken refreshToken = oAuthResponse.getRefreshToken().get();
                    String refreshTokenToken = refreshToken.getToken();
                    log.info("refreshToken is ===> {}", refreshTokenToken);
                    Date refreshTokenExpiresOn = refreshToken.getExpiresOn();
                    log.info("refreshTokenExpiresOn is ===> {}", refreshTokenExpiresOn);
                    TokenType refreshTokenType = refreshToken.getTokenType();
                    log.info("refreshTokenType is ===> {}", refreshTokenType);
                    sysConfigDO.setRefreshToken(refreshTokenToken);
                    sysConfigDO.setFreshExpireTime(refreshTokenExpiresOn);
                    sysConfigDO.setUpdateTime(new Date());
                    sysConfigDO.setConfigType(tokenType.name());
                }
            }
            sysConfigRepository.save(sysConfigDO);
        }

        return token;
    }

}
