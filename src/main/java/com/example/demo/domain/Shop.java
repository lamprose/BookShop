package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shopping")
public class Shop {

    @GeneratedValue
    @Id
    private Integer sId;

    //订单编号
    @Column
    private String orderNum;

    //用户id
    @Column
    private String id;

    //书籍名称
    @Column
    private String title;

    //数量
    @Column
    private Integer sum;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
