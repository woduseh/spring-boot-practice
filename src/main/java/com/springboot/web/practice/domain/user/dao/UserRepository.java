package com.springboot.web.practice.domain.user.dao;

import com.springboot.web.practice.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.domain.user.dao
 * fileName         : UserRepository
 * author           : JYHwang
 * date             : 2022-03-22
 * description      : UserRepository DAO
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-22           JYHwang                 최초 생성
 * </pre>
 */

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
