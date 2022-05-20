package com.springboot.web.practice.domain.user.entity;

import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.post.entity.Posts;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column
  private String picture;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Builder
  public User(String name, String email, String picture, Role role) {
    this.name = name;
    this.email = email;
    this.picture = picture;
    this.role = role;
  }

  public User(SessionUser sessionUser) {
    this.id = sessionUser.getId();
    this.name = sessionUser.getName();
    this.email = sessionUser.getEmail();
    this.picture = sessionUser.getPicture();
    this.role = sessionUser.getRole();
  }

  public User update(String name, String picture) {
    this.name = name;
    this.picture = picture;

    return this;
  }

  public String getRoleKey() {
    return this.role.getKey();
  }

  @OneToMany(mappedBy = "author")
  private Collection<Posts> posts;

  public Collection<Posts> getPosts() {
    return posts;
  }

  public void setPosts(Collection<Posts> posts) {
    this.posts = posts;
  }
}
