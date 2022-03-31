package com.springboot.web.practice.domain.home;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.domain.home
 * fileName         : HomeController
 * author           : JYHwang
 * date             : 2022-03-30
 * description      :
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-30           JYHwang                 최초 생성
 * </pre>
 */

@Controller
public class HomeController implements ErrorController {

  @GetMapping({"/", "/error"})
  public String vue() {
    return "vue";
  }

  @RequestMapping(value = "/error")
  public RedirectView getErrorPath() {
    return new RedirectView("/error");
  }
}