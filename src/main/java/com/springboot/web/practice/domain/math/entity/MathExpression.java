package com.springboot.web.practice.domain.math.entity;

import com.springboot.web.practice.domain.user.entity.User;
import com.springboot.web.practice.global.common.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @Exclude
  private User user;

  public void setUser(User user) {
    this.user = user;
  }

  @Builder
  public MathExpression(Long id, String expression, String correctAnswer, User user) {
    this.id = id;
    this.expression = expression;
    this.correctAnswer = correctAnswer;
    this.user = user;
  }
}
