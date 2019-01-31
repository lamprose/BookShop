package com.example.demo.repository;

import com.example.demo.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    /*Message findOneByUseridAndDate(String userid, Date date);*/

}
