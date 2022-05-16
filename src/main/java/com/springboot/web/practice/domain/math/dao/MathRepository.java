package com.springboot.web.practice.domain.math.dao;

import com.springboot.web.practice.domain.math.dto.response.MathResponseDto;
import com.springboot.web.practice.domain.math.entity.MathExpression;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MathRepository extends JpaRepository<MathExpression, Long> {
  List<MathResponseDto> findAllById(Long id);
}
