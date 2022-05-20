package com.springboot.web.practice.domain.post.service;

import com.springboot.web.practice.domain.post.dao.PostsRepository;
import com.springboot.web.practice.domain.post.dto.request.PostsUpdateRequestDto;
import com.springboot.web.practice.domain.post.dto.response.PostsListResponseDto;
import com.springboot.web.practice.domain.post.dto.response.PostsResponseDto;
import com.springboot.web.practice.domain.post.dto.response.PostsSaveRequestDto;
import com.springboot.web.practice.domain.post.entity.Posts;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @Transactional(timeout = 10)
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional(timeout = 10)
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts post = postsRepository.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("Update Error - ID: " + id + " 인 게시글이 없습니다."));

    post.update(requestDto.getTitle(), requestDto.getContent());

    return id;
  }

  @Transactional(timeout = 10)
  public void delete(Long id) {
    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

    postsRepository.delete(posts);
  }

  @Transactional(timeout = 10)
  public PostsResponseDto findById(Long id) {
    Posts post = postsRepository.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("Select Error - ID: " + id + " 인 게시글이 없습니다."));

    return new PostsResponseDto(post);
  }

  @Transactional(timeout = 10)
  public List<PostsListResponseDto> findAllDesc() {
    return postsRepository.findAllDesc().stream()
        .map(PostsListResponseDto::new)
        .collect(Collectors.toList());
  }
}
