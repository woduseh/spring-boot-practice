package com.springboot.web.practice.domain.post.dto.response;

import com.springboot.web.practice.domain.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

  private Long Id;
  private String title;
  private String content;
  private String author;

  public PostResponseDto(Post post) {
    this.Id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.author = post.getAuthor();
  }
}
