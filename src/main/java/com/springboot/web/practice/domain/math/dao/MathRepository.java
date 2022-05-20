package com.springboot.web.practice.domain.math.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface MathRepository extends MathJpaRepository, MathCustomRepository {

}
