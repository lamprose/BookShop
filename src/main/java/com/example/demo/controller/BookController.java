package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book",produces = { "application/json;charset=UTF-8"})
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/{type}")
    public List<Book> getUser(@PathVariable("type") String type){
        return bookService.findAllByType(type);
    }

    @GetMapping("/findAll")
    public List<Book> findallmessage(){return bookService.findAll();}

    @PostMapping("/saveOne")
    public boolean saveOne(@RequestBody Book book){return bookService.saveOne(book);}

    @PostMapping("/updateOne")
    public String  updateOne(@RequestBody Book book){return bookService.updateOne(book);}

    @GetMapping("/deleteOne/{title}")
    public String deleteOne(@PathVariable("title") String title){
        return bookService.deleteOne(title);
    }

    //通过isbn查询记录
    @GetMapping("/findOneByIsbn/{isbn}")
    public Book findOneByIsbn(@PathVariable("isbn") String isbn) {return bookService.findOneByIsbn(isbn);}

    //根据输入数据模糊查询
    @GetMapping("/findAllSearch/{keyword}")
    public List<Book> findAllSearch(@PathVariable("keyword") String keyword) {
        return bookService.findAllSearch("%" + keyword + "%");
    }
}
