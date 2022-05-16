package com.springboot.web.practice.domain.math.service;

import com.springboot.web.practice.domain.math.dao.MathRepository;
import com.springboot.web.practice.domain.math.dto.request.MathRequestDto;
import com.springboot.web.practice.domain.math.dto.response.MathResponseDto;
import com.springboot.web.practice.domain.math.utils.EvalUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;

@RequiredArgsConstructor
@Service
public class MathService {
  private final MathRepository mathRepository;
  private final EvalUtil evalUtil;

  public List<MathResponseDto> findAllById(Long id) {
    return mathRepository.findAllById(id);
  }

  public Long save(MathRequestDto requestDto) throws ScriptException {
    return mathRepository.save(requestDto.toEntity(requestDto, getCorrectAnswer(requestDto.getExpression()))).getId();
  }

  public String getCorrectAnswer(String expression) throws ScriptException {
    if (checkAnswer(expression)) {
      return "";
    } else {
      return calculate(expression.split("=")[0]);
    }
  }

  public boolean checkAnswer(String expression) throws ScriptException {
    String[] expressionArray = expression.split("=");
    String calcExpression = expressionArray[0];
    String answerExpression = expressionArray[1];

    return calculate(calcExpression).equals(answerExpression.trim());
  }

  public String calculate (String calcExpression) throws ScriptException {
    return evalUtil.eval(calcExpression).toString();
  }
}

