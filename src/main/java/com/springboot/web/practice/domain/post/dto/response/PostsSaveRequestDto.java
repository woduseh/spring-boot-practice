package com.springboot.web.practice.domain.post.dto.response;

import com.springboot.web.practice.domain.post.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

  private String title;
  private String content;
  private String author;

  @Builder
  public PostsSaveRequestDto(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public Posts toEntity() {
    return Posts.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
  }
}
