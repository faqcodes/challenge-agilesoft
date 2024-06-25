package com.faqcodes.agilesoft.users.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserData, String> {

  @Query(value = "SELECT CASE WHEN COUNT(u.ID) > 0 THEN true ELSE false END FROM USERS u WHERE username = :username", nativeQuery = true)
  boolean existsByUsername(@Param("username") String username);

}