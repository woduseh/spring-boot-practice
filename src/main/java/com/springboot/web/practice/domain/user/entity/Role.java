package com.springboot.web.practice.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.domain.user
 * fileName         : Role
 * author           : JYHwang
 * date             : 2022-03-22
 * description      : User의 권한을 관리하는 Enum
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-22           JYHwang                 최초 생성
 * </pre>
 */

@Getter
@RequiredArgsConstructor
public enum Role {
  GUEST("ROLE_GUEST", "손님"),
  USER("ROLE_USER", "일반 사용자");

  private final String key;
  private final String title;
}
