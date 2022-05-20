package com.springboot.web.practice.domain.math.controller;

import com.springboot.web.practice.config.auth.LoginUser;
import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.math.service.MathService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/maths")
public class MathController {

  private static final Logger log = LoggerFactory.getLogger(MathController.class);

  private final MathService mathService;

  @GetMapping
  public String checkMathList(Model model, @LoginUser SessionUser user) {
    model.addAttribute("maths", mathService.findAllById(user.getId()));
    model.addAttribute("loginUserName", user.getName());
    return "maths/maths";
  }

  @GetMapping("/save")
  public String mathsSave(Model model, @LoginUser SessionUser user) {
    model.addAttribute("loginUserName", user.getName());
    return "maths/maths-save";
  }
}
