package com.springboot.web.practice.config.auth.dto;

import com.springboot.web.practice.domain.user.entity.User;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {

  private final String name;
  private final String email;
  private final String picture;

  public SessionUser(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.picture = user.getPicture();
  }
}