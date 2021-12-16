package com.springboot.web.practice.domain.post.entity;

import com.springboot.web.practice.global.common.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_post")
public class Post extends BaseTimeEntity {

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
