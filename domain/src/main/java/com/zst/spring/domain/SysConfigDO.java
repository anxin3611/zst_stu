package com.zst.spring.domain;

import com.zst.spring.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/23 13:15
 * @description ：表 sys_config
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_config")
@Data
public class SysConfigDO extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "app_id")
    private String appId;
    @Column(name = "cert_id")
    private String certId;
    @Column(name = "dev_id")
    private String devId;
    @Column(name = "redirect_uri")
    private String redirectUri;
    @Column(name = "config_name")
    private String configName;
    @Column(name = "config_type")
    private String configType;
    private String token;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "expire_time")
    private Date expireTime;
    @Column(name = "fresh_expire_time")
    private Date freshExpireTime;
    @Column(name = "in_use")
    private Boolean inUse;
    @Column(name = "auth_url")
    private String authUrl;
}
