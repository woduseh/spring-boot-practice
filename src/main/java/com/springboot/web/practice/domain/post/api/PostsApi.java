package com.springboot.web.practice.domain.post.api;

import com.springboot.web.practice.config.auth.LoginUser;
import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.post.dto.request.PostsUpdateRequestDto;
import com.springboot.web.practice.domain.post.dto.response.PostsListResponseDto;
import com.springboot.web.practice.domain.post.dto.response.PostsResponseDto;
import com.springboot.web.practice.domain.post.dto.response.PostsSaveRequestDto;
import com.springboot.web.practice.domain.post.service.PostsService;
import com.springboot.web.practice.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostsApi {

  private final PostsService postService;

  @PostMapping
  public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser user) {
    return postService.save(requestDto.toBuilder()
        .author(new User(user))
        .build());
  }

  @PatchMapping("/{id}")
  public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
    return postService.update(id, requestDto);
  }

  @DeleteMapping("/{id}")
  public Long delete(@PathVariable Long id) {
    postService.delete(id);
    return id;
  }

  @GetMapping("/{id}")
  public PostsResponseDto findById(@PathVariable Long id) {
    return postService.findById(id);
  }

  @GetMapping("list")
  public List<PostsListResponseDto> findAll() {
    return postService.findAllDesc();
  }
}
