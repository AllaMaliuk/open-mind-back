package com.open.mind.back.repository;

import com.open.mind.back.entity.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> getUserEntityByEmail(String email);

  @Transactional
  @Modifying
  @Query("update User u set u.enabled = true where u.email = :email")
  void findByEmailAndEnable(@Param(value = "email") String email);

  @Transactional
  @Modifying
  @Query("update User u set u.password = :newPassword where u.email = :email")
  void findByEmailAndChangePassword(
      @Param(value = "email") String email, @Param(value = "newPassword") String newPassword);
}
