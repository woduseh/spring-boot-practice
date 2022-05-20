package com.springboot.web.practice.domain.math.dto.request;

import com.springboot.web.practice.domain.math.entity.MathExpression;
import com.springboot.web.practice.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder(toBuilder = true)
public class MathSaveRequestDto {

  private String expression;
  private User user;

  @Builder
  public MathSaveRequestDto(String expression, User user) {
    this.expression = expression;
    this.user = user;
  }

  public MathExpression toEntity(MathSaveRequestDto requestDto, String correctAnswer) {
    return MathExpression.builder()
        .expression(requestDto.getExpression())
        .correctAnswer(correctAnswer)
        .user(requestDto.getUser())
        .build();
  }
}
