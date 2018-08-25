package com.renguangli.mamba.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * KuaiDi
 *
 * @author renguangli 2018/2/27 21:13
 * @since JDK 1.8
 */
@Entity
@Table(name = "KUAIDI")
public class KuaiDi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private String no;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date;

    @Transient
    private int state;

    @Transient
    private int intervalHour;

    /**
     * 1 正常
     * 0 可能不正常
     */
    @Transient
    private int classify;

    @Transient
    private String data;

    public KuaiDi(){}

    public KuaiDi(String code, String name, String no, Date date, int state, int intervalHour, int classify, String data) {
        this.code = code;
        this.name = name;
        this.no = no;
        this.date = date;
        this.state = state;
        this.intervalHour = intervalHour;
        this.classify = classify;
        this.data = data;
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

    public String getNo() {
        return no;
    }

    public void setNo(String nu) {
        this.no = nu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIntervalHour() {
        return intervalHour;
    }

    public void setIntervalHour(int intervalHour) {
        this.intervalHour = intervalHour;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "KuaiDi{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", date=" + date +
                ", state=" + state +
                ", intervalHour=" + intervalHour +
                ", classify=" + classify +
                ", data=" + data +
                '}';
    }
}
