package com.renguangli.mamba.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * KuaiDi
 *
 * @author renguangli 2018/2/27 21:13
 * @since JDK 1.8
 */
@Entity
@Table(name = "KUAIDI_DIR")
public class KuaiDiDir {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String code;

    private String name;

    public KuaiDiDir(){}

    public KuaiDiDir(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "KuaiDiDir{" +
                "id=" + id +
                ", code=" + code +
                ", name=" + name +
                "}";
    }
}
