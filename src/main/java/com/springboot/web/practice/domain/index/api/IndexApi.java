package com.springboot.web.practice.domain.index.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexApi {

  @GetMapping("/")
  public String index() {
    return "index";
  }
}
