package com.springboot.web.practice.domain.index.controller;

import com.springboot.web.practice.config.auth.LoginUser;
import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.post.dto.response.PostsResponseDto;
import com.springboot.web.practice.domain.post.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    return "posts/posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public Object postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
    PostsResponseDto postsResponseDto = postsService.findById(id);

    if (postsResponseDto.getId().equals(user.getId())) {
      model.addAttribute("post", postsResponseDto);

      return "posts/posts-update";
    } else {
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body("수정 권한이 없습니다!");
    }
  }

  @GetMapping("/posts/view/{id}")
  public String postsView(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
    PostsResponseDto postsResponseDto = postsService.findById(id);
    model.addAttribute("post", postsResponseDto);

    boolean hasEditAuthority = postsResponseDto.getId().equals(user.getId());
    model.addAttribute("editAuthority", hasEditAuthority);

    return "posts/posts-view";
  }
}
