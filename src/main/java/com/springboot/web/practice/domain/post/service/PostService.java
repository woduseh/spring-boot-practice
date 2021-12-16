package com.springboot.web.practice.domain.post.service;

import com.springboot.web.practice.domain.post.dao.PostRepository;
import com.springboot.web.practice.domain.post.dto.request.PostUpdateRequestDto;
import com.springboot.web.practice.domain.post.dto.response.PostListResponseDto;
import com.springboot.web.practice.domain.post.dto.response.PostResponseDto;
import com.springboot.web.practice.domain.post.dto.response.PostSaveRequestDto;
import com.springboot.web.practice.domain.post.entity.Post;
import java.util.List;
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

  @Transactional(timeout = 10)
  public Long update(Long id, PostUpdateRequestDto requestDto) {
    Post post = postRepository.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("Update Error - ID: " + id + " 인 게시글이 없습니다."));
    
    post.update(requestDto.getTitle(), requestDto.getContent());

    return id;
  }

  @Transactional(timeout = 10)
  public void delete(Long id) {

  }

  @Transactional(timeout = 10)
  public PostResponseDto findById(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("Select Error - ID: " + id + " 인 게시글이 없습니다."));

    return new PostResponseDto(post);
  }

  @Transactional(timeout = 10)
  public List<PostListResponseDto> findAllDesc() {
    return null;
  }
}
