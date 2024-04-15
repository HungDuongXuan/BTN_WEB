package com.dzungyb.btn_web.repository;

import com.dzungyb.btn_web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByRoles(String role);

    boolean existsById(Integer id);

    boolean existsByEmail(String email);

    User findFirstById(Integer id);

    User findFirstByEmail(String email);

    Optional<User> findByEmail(String email);

    User findFirstByUsername(String username);
}
