package com.zst.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Entity
@Table(name = "corporation")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CorporationDO implements Serializable {
    @Id
    @Column(name = "corp_id")
    private Short corpId;
    private String name;
}
