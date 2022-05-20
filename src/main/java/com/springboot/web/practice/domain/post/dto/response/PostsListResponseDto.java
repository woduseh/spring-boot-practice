package com.springboot.web.practice.domain.post.dto.response;

import com.springboot.web.practice.domain.post.entity.Posts;
import com.springboot.web.practice.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostsListResponseDto {

  private final Long id;
  private final String title;
  private final User author;
  private final LocalDateTime modifiedDate;

  public PostsListResponseDto(Posts entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.author = entity.getAuthor();
    this.modifiedDate = entity.getModifiedDate();
  }
}
