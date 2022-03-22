package com.springboot.web.practice.config.auth.dto;

import com.springboot.web.practice.domain.user.entity.User;
import java.io.Serializable;
import lombok.Getter;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.config.auth.dto
 * fileName         : SessionUser
 * author           : JYHwang
 * date             : 2022-03-22
 * description      : User Entity의 직렬화용 DTO
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-22           JYHwang                 최초 생성
 * </pre>
 */

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