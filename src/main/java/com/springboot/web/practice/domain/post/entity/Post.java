package com.springboot.web.practice.domain.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Post {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", length = 500, nullable = false)
  private String title;

  @Column(name = "content", columnDefinition = "TEXT", nullable = false)
  private String content;

  @Column(name = "author")
  private String author;

  @Builder
  public Post(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
