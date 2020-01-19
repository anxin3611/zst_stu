package com.zst.spring.domain;

import com.zst.spring.base.BaseDO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/10 14:23
 * @description DO - 与数据库一一对应的实体
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "corporation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporationDO extends BaseDO {
    private static final long serialVersionUID = 568187596679037147L;
    @Id
    @Column(name = "corp_id")
    private Short corpId;
    private String name;
}
