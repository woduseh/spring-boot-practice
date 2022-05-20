package com.springboot.web.practice.domain.math.api;

import com.springboot.web.practice.config.auth.LoginUser;
import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.math.dto.request.MathSaveRequestDto;
import com.springboot.web.practice.domain.math.dto.response.MathResponseDto;
import com.springboot.web.practice.domain.math.service.MathService;
import com.springboot.web.practice.domain.user.entity.User;
import java.util.List;
import javax.script.ScriptException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping("/save")
  public Long save(@RequestBody MathSaveRequestDto requestDto, @LoginUser SessionUser user)
      throws ScriptException {
    return mathService.save(requestDto.toBuilder()
        .user(new User(user))
        .build());
  }

  @GetMapping("/{id}")
  public List<MathResponseDto> findHistory(@PathVariable Long id) {
    return mathService.findAllById(id);
  }
}
