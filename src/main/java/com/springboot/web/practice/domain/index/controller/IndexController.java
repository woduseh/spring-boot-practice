package com.springboot.web.practice.domain.index.controller;

import com.springboot.web.practice.config.auth.LoginUser;
import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.post.dto.response.PostsResponseDto;
import com.springboot.web.practice.domain.post.service.PostsService;
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

  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user) {
    model.addAttribute("posts", postsService.findAllDesc());

    if (user != null) {
      model.addAttribute("loginUserName", user.getName());
    }

    return "index";
  }

  @GetMapping("/posts/save")
  public String postsSave(Model model, @LoginUser SessionUser user) {
    if (user != null) {
      model.addAttribute("loginUserName", user.getName());
    }
    return "posts-save";
  }

  // 수정방안 - PostsResponseDto 의 Author가 현재 로그인한 User와 일치할 경우 업데이트, 아니면 view 출력
  // 혹은 그냥 전부 view 출력하고 수정하기 버튼을 누르면 권한 체크 후 update로 이동
  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model) {
    PostsResponseDto postsResponseDto = postsService.findById(id);
    model.addAttribute("post", postsResponseDto);

    return "posts-update";
  }

  @GetMapping("/posts/view/{id}")
  public String postsView(@PathVariable Long id, Model model) {
    PostsResponseDto postsResponseDto = postsService.findById(id);
    model.addAttribute("post", postsResponseDto);

    return "posts-view";
  }
}
