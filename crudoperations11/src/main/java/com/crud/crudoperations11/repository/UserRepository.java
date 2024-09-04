package com.crud.crudoperations11.repository;


import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crud.crudoperations11.model.User;



@Repository
 public interface UserRepository extends JpaRepository<User,Long>{

  List<User>findByName(String name);
  List<User>findByLocation(String location);


  
  List<User> findByAgeLessThan(Integer age);
  
  
  @Query("SELECT u FROM User u WHERE u.name LIKE :prefix%")
  List<User>findByNameStartsWith(@Param("prefix") String prefix);
  
}