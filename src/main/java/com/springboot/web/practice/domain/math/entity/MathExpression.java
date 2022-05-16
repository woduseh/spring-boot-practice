package com.springboot.web.practice.domain.math.entity;

import com.springboot.web.practice.global.common.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "tb_math_expression")
public class MathExpression extends BaseTimeEntity {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "expression", length = 1000, nullable = false)
  private String expression;

  @Column(name = "correct_answer", length = 1000)
  private String correctAnswer;

  @Builder
  public MathExpression(Long id, String expression, String correctAnswer) {
    this.id = id;
    this.expression = expression;
    this.correctAnswer = correctAnswer;
  }
}
