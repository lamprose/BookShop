package com.example.demo.repository;
import com.example.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findAllByType(String type);
    List<Book> findBooksByType(String type);
    Book findOneByTitle(String title);
    Book findOneByIsbn(String isbn);

    /*通过title和author模糊查询书籍信息*/
    List<Book> findByTitleLike(String title);
    List<Book> findByAuthorLike(String author);
    /*通过title和author模糊查询书籍信息*/

    @Transactional
    @Modifying
    @Query(value = "update Book s set s.author =:author,s.img =:img,s.isbn =:isbn,s.type =:type where s.title = :title")
    int updateOne(@Param("title") String title, @Param("author") String author, @Param("img") String img, @Param("isbn") String isbn, @Param("type") String type);
}
