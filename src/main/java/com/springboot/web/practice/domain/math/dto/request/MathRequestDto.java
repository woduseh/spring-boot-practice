package com.springboot.web.practice.domain.math.dto.request;

import com.springboot.web.practice.domain.math.entity.MathExpression;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MathRequestDto {
  private Long id;
  private String expression;

  @Builder
  public MathRequestDto(Long id, String expression) {
    this.id = id;
    this.expression = expression;
  }

  public MathExpression toEntity(MathRequestDto requestDto, String correctAnswer) {
    return MathExpression.builder()
        .id(requestDto.getId())
        .expression(requestDto.getExpression())
        .correctAnswer(correctAnswer)
        .build();
  }
}
