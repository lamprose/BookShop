package com.example.demo.service;
import com.example.demo.domain.Book;
import java.util.List;
public interface BookService {
    //查询this.type = type 的所有Book记录
    public List<Book> findAllByType(String type);

    //查询所有的Book
    List<Book> findAll();

    //保存一个Book
    boolean saveOne(Book user);

    //更新一个Book
    String updateOne(Book user);

    //删除一个Book
    String  deleteOne(String title);

    //通过isbn查询书籍
    Book findOneByIsbn(String isbn);

    //通过传入的关键字模糊查询
    List<Book> findAllSearch(String keyword);
}
