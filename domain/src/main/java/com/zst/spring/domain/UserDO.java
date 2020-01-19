package com.zst.spring.domain;

import com.zst.spring.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 13:05
 * @description 用户表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
public class UserDO extends BaseDO {
    private static final long serialVersionUID = 8347107355226154207L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    private String username;
    private String password;
    private String salt;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_id")
    private long createId;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "update_id")
    private long updateId;
}
