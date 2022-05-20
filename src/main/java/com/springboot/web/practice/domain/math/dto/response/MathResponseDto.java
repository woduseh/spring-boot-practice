package com.springboot.web.practice.domain.math.dto.response;


import com.springboot.web.practice.domain.math.entity.MathExpression;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MathResponseDto {

  private Long id;
  private String expression;
  private String correctAnswer;
  private LocalDateTime createdDate;

  @Builder
  public MathResponseDto(MathExpression entity) {
    this.id = entity.getId();
    this.expression = entity.getExpression();
    this.correctAnswer = entity.getCorrectAnswer();
    this.createdDate = entity.getCreatedDate();
  }

  public MathResponseDto(MathResponseDto mathResponseDto) {
  }
}
