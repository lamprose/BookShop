package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_info")
public class Book {
    //主键
    @GeneratedValue
    @Id
    private Integer gId;

    //书名
    @Column
    private String title;

    //类型
    @Column
    private  String type;

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    //作者
    @Column
    private String author;

    //ISBN号
    @Column
    private String isbn;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBooksum() {
        return bookSum;
    }

    public void setBooksum(Integer booksum) {
        this.bookSum = booksum;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    //出版社
    @Column
    private String press;

    //价格
    @Column
    private Double price;

    //出版日期
    @Column
    private String date;

    //数量
    @Column
    private Integer bookSum;

    //备注
    @Column
    private String others;

    //封面
    @Column
    private String img;
}
