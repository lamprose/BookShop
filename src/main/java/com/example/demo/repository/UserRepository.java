package com.example.demo.repository;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findOneById(String id);

    @Transactional
    @Modifying
    @Query(value = "update User s set s.name =:name,s.password =:password,s.phone =:phone,s.address =:address,s.sex =:sex,s.flag =:flag  where s.id = :id")
    int updateOne(@Param("id") String id, @Param("name") String name, @Param("password") String password, @Param("phone") String phone, @Param("address") String address, @Param("sex") Integer sex, @Param("flag") Integer flag);

    @Transactional
    @Modifying
    @Query(value = "update User s set s.password =:password  where s.id = :id")
    int updateOnePassword(@Param("id") String id,  @Param("password") String password);

}
