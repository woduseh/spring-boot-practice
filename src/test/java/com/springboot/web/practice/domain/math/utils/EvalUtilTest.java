package com.springboot.web.practice.domain.math.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.script.ScriptException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EvalUtilTest {

  private final EvalUtil evalUtil = new EvalUtil();

  @Test
  @DisplayName("Eval 테스트")
  void evalTest() throws ScriptException {
    // given
    String expression = """
        23 * 4 - 12 + (2 * 5) * 11 / 10 + 1
        """;
    int answer = 92;

    // when
    int evalExpression = (int) evalUtil.eval(expression);

    // then
    assertEquals(evalExpression, answer);
  }
}
