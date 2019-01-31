package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllByType(String type){
        return bookRepository.findAllByType(type);
    }

    @Override
    public List<Book> findAll() {
        List<Book> list = bookRepository.findAll();
        return list;
    }

    @Override
    public boolean saveOne(Book book){
        boolean flag = false;
        try{
            bookRepository.save(book);
            flag =true;
        }catch (Exception e){
            System.out.println("保存失败！");
        }
        return flag;
    }

    @Override
    public String updateOne(Book book) {
        boolean flag1 = false;
        String title = book.getTitle();
        String author = book.getAuthor();
        String img = book.getImg();
        String isbn = book.getIsbn();
        String type = book.getType();
        try {
            bookRepository.updateOne(title,author,img,isbn,type);
            flag1 = true;
        }catch (Exception e){
            System.out.println("update search!");
        }
        return flag1 == true? "success" : "failed";
    }

    @Override
    public String deleteOne(String title) {
        Book book = bookRepository.findOneByTitle(title);
        if(book == null){
            return "search failed！";
        }
        boolean flag = false;
        try {
            bookRepository.delete(book);
            flag =true;
        }catch (Exception e){
            System.out.println("delete faild");
        }
        return flag == true? "success" : "failed";
    }

    //通过isbn查找一个书籍记录
    @Override
    public Book findOneByIsbn(String isbn){
        return bookRepository.findOneByIsbn(isbn);
    }

    //通过关键字模糊查询书籍记录
    @Override
    public List<Book> findAllSearch(String keyword){
        List<Book> list1;
        List<Book> list2;
        list1 = bookRepository.findByTitleLike(keyword);
        list2 = bookRepository.findByAuthorLike(keyword);
        for (int i=0; i<list2.size(); i++){
            list1.add(list2.get(i));
        }
        return list1;
    }
}
