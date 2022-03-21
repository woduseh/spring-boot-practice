package com.springboot.web.practice.domain.post.dto.response;

import com.springboot.web.practice.domain.post.entity.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

  private Long id;
  private String title;
  private String content;
  private String author;

  public PostsResponseDto(Posts post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.author = post.getAuthor();
  }
}
