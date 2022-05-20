package com.springboot.web.practice.domain.math.dao;

import com.springboot.web.practice.domain.math.entity.MathExpression;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathJpaRepository extends JpaRepository<MathExpression, Long> {

  List<MathExpression> findAllById(Long id);
}
