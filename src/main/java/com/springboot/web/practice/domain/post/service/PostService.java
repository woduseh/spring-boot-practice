package com.springboot.web.practice.domain.post.service;

import com.springboot.web.practice.domain.post.dao.PostRepository;
import com.springboot.web.practice.domain.post.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

  private final PostRepository postRepository;

  @Transactional(timeout = 10)
  public Long save(PostSaveRequestDto requestDto) {
    return postRepository.save(requestDto.toEntity()).getId();
  }

/*  public Long update(Long id, PostUpdateRequestDto requestDto) {
  }

  public void delete(Long id) {
  }

  public PostResponseDto findById(Long id) {
  }

  public List<PostListResponseDto> findAllDesc() {
  }*/
}
