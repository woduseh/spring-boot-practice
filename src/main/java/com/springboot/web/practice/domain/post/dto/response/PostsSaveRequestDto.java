package com.springboot.web.practice.domain.post.dto.response;

import com.springboot.web.practice.domain.post.entity.Posts;
import com.springboot.web.practice.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder(toBuilder = true)
public class PostsSaveRequestDto {

  private String title;
  private String content;
  private User author;

  @Builder
  public PostsSaveRequestDto(String title, String content, User author) {
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
