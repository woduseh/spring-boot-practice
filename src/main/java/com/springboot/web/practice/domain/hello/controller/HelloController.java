package com.springboot.web.practice.domain.hello.controller;

import com.springboot.web.practice.domain.hello.dto.HelloResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/hello/dto")
  public HelloResponseDto helloDto(@RequestParam("name") String name,
      @RequestParam("amount") int amount) {
    return new HelloResponseDto(name, amount);
  }
}
