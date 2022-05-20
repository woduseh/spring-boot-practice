package com.springboot.web.practice.domain.math.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.springboot.web.practice.domain.math.dao.MathJpaRepository;
import com.springboot.web.practice.domain.math.utils.EvalUtil;
import javax.script.ScriptException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MathServiceTest {

  @Mock
  private MathJpaRepository mathRepository;
  private MathService mathService;
  private final EvalUtil evalUtil = new EvalUtil();

  @BeforeEach
  void setup  () {
    this.mathService = new MathService(mathRepository, evalUtil);
  }

  @Test
  @DisplayName("수식 검증 기능")
  void checkAnswerTest() throws ScriptException {
    // given
    String wrongExpression = "1 + 2 = 4";
    String correctExpression = "1 + 2 = 3";
    String errorExpression = "가 + 나 = 가나";

    // when
    boolean wrongAnswer = mathService.checkAnswer(wrongExpression);
    boolean correctAnswer = mathService.checkAnswer(correctExpression);

    // then
    assertThat(wrongAnswer).isFalse();
    assertThat(correctAnswer).isTrue();
    assertThatThrownBy(() -> mathService.checkAnswer(errorExpression)).isInstanceOf(ScriptException.class);
  }

  @Test
  @DisplayName("올바른 정답 출력 기능")
  void getCorrectAnswerTest() throws ScriptException {
    String wrongExpression = "1 + 2 = 4";
    String correctExpression = "1 + 2 = 3";
    String errorExpression = "가 + 나 = 가나";

    String wrongAnswer = mathService.getCorrectAnswer(wrongExpression);
    String correctAnswer = mathService.getCorrectAnswer(correctExpression);

    assertThat(wrongAnswer).isEqualTo("3");
    assertThat(correctAnswer).isEmpty();
    assertThatThrownBy(() -> mathService.getCorrectAnswer(errorExpression)).isInstanceOf(ScriptException.class);
  }
}
