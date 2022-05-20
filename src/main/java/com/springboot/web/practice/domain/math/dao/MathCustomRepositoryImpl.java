package com.springboot.web.practice.domain.math.dao;

import static com.springboot.web.practice.domain.math.entity.QMathExpression.mathExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.web.practice.domain.math.entity.MathExpression;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MathCustomRepositoryImpl implements MathCustomRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public MathCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<MathExpression> findAllByUserId(Long id) {
    return jpaQueryFactory.selectFrom(mathExpression)
        .where(mathExpression.user.id.eq(id))
        .fetch();
  }
}
