package com.springboot.web.practice.domain.post.api;

import com.springboot.web.practice.domain.post.dto.request.PostUpdateRequestDto;
import com.springboot.web.practice.domain.post.dto.response.PostListResponseDto;
import com.springboot.web.practice.domain.post.dto.response.PostResponseDto;
import com.springboot.web.practice.domain.post.dto.response.PostSaveRequestDto;
import com.springboot.web.practice.domain.post.service.PostService;
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
@RequestMapping("/api/post")
public class PostApi {

  private final PostService postService;

  @PostMapping
  public Long save(@RequestBody PostSaveRequestDto requestDto) {
    return postService.save(requestDto);
  }

  @PatchMapping("/{id}")
  public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
    return postService.update(id, requestDto);
  }

  @DeleteMapping("/{id}")
  public Long delete(@PathVariable Long id) {
    postService.delete(id);
    return id;
  }

  @GetMapping("/{id}")
  public PostResponseDto findById(@PathVariable Long id) {
    return postService.findById(id);
  }

  @GetMapping("list")
  public List<PostListResponseDto> findAll() {
    return postService.findAllDesc();
  }
}
