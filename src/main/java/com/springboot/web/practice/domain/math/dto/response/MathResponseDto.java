package com.springboot.web.practice.domain.math.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MathResponseDto {
  private Long id;
  private String expression;
  private String correctAnswer;

  @Builder
  public MathResponseDto(Long id, String expression, String correctAnswer) {
    this.id = id;
    this.expression = expression;
    this.correctAnswer = correctAnswer;
  }
}
