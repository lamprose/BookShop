package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_info")
public class User {
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getSecretOption() { return secretOption; }

    public void setSecretOption(Integer secretOption) { this.secretOption = secretOption; }

    public String getSecret() { return secret; }

    public void setSecret(String secret) { this.secret = secret; }

    //主键
    @GeneratedValue
    @Id
    private Integer uId;

    //用户登录账号
    @Column(length = 20,nullable = false)
    private String id;

    //用户自定义姓名
    @Column(length = 20)
    private String name;

    //用户性别
    @Column
    private Integer sex;

    //密码
    @Column
    private String password;

    //号码
    @Column
    private String phone;

    //地址
    @Column
    private String address;

    //是否为管理员标志
    @Column
    private Integer flag;

    //密保问题类型
    @Column
    private Integer secretOption;

    //密保问题答案
    @Column
    private String secret;
}
