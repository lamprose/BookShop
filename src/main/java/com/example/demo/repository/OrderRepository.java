package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findOneByOrderId( String orderId);

    @Transactional
    @Modifying
    @Query(value = "update Order s set s.orderSource = :orderSource,s.totalPrice =:totalPrice,s.remark =:remark  where s.orderId = :orderId")
    int updateOrder(@Param("orderId") String orderId,@Param("orderSource") String orderSource, @Param("totalPrice") String totalPrice, @Param("remark") String remark);
}
