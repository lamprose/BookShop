package com.example.demo.repository;

import com.example.demo.domain.Book;
import com.example.demo.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Integer> {

    Shop findOneByOrderNum(String ordernum);

    Shop findOneById(String id);

    List<Shop> findAllById(String id);

    @Transactional
    @Modifying
    @Query(value = "update Shop s set s.id =:id,s.title =:title,s.sum =:sum where s.orderNum = :orderNum")
    int updateOne(@Param("orderNum") String orderNum, @Param("id") String id, @Param("title") String title, @Param("sum") Integer sum);

}
