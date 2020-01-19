package com.zst.spring.domain;

import com.zst.spring.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/15 14:15
 * @description 序列号规则-DO
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "identity")
@Data
public class IdentityDO extends BaseDO {
    private static final long serialVersionUID = -4204761469063437215L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "alias")
    private String alias;
    @Column(name = "rule")
    private String rule;
    @Column(name = "get_type")
    private Byte type;
    @Column(name = "no_length")
    private Integer length;
    @Column(name = "init_value")
    private Integer initValue;
    @Column(name = "cur_value")
    private Integer curValue;
    @Column(name = "step")
    private Integer step;
    @Column(name = "cur_date")
    private String curDate;
}
