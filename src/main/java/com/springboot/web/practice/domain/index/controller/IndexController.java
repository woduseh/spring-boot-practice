package com.springboot.web.practice.domain.index.controller;

import com.springboot.web.practice.config.auth.LoginUser;
import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.post.dto.response.PostsResponseDto;
import com.springboot.web.practice.domain.post.service.PostsService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

  private static final Logger log = LoggerFactory.getLogger(IndexController.class);
  private final PostsService postsService;
  private final HttpSession httpSession;

  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user) {
    model.addAttribute("posts", postsService.findAllDesc());

    if (user != null) {
      model.addAttribute("loginUserName", user.getName());
    }

    return "index";
  }

  @GetMapping("/posts/save")
  public String postsSave() {
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model) {
    PostsResponseDto postsResponseDto = postsService.findById(id);
    model.addAttribute("post", postsResponseDto);

    return "posts-update";
  }
}
