package com.springboot.web.practice.domain.math.dao;

import com.springboot.web.practice.domain.math.entity.MathExpression;
import java.util.List;

public interface MathCustomRepository {

  List<MathExpression> findAllByUserId(Long id);
}
