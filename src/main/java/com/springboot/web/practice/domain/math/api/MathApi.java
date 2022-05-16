package com.springboot.web.practice.domain.math.api;

import com.springboot.web.practice.domain.math.dto.request.MathRequestDto;
import com.springboot.web.practice.domain.math.dto.response.MathResponseDto;
import com.springboot.web.practice.domain.math.service.MathService;
import java.util.List;
import javax.script.ScriptException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maths")
public class MathApi {

  private final MathService mathService;

  @PostMapping
  public Long save(@RequestBody MathRequestDto requestDto) throws ScriptException {
    return mathService.save(requestDto);
  }

  @GetMapping
  public List<MathResponseDto> findHistory(Long id) {
    return mathService.findAllById(id);
  }
}
