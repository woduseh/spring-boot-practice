package com.springboot.web.practice.domain.post.dto.response;

import com.springboot.web.practice.domain.post.entity.Posts;
import com.springboot.web.practice.domain.user.entity.User;
import lombok.Getter;

@Getter
public class PostsResponseDto {

  private final Long id;
  private final String title;
  private final String content;
  private final User author;

  public PostsResponseDto(Posts post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.author = post.getAuthor();
  }
}
