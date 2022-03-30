package com.springboot.web.practice.domain.profile.controller;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.domain.profile.controller
 * fileName         : ProfileController
 * author           : JYHwang
 * date             : 2022-03-30
 * description      :
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-30           JYHwang                 최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@RestController
public class ProfileController {

  private final Environment env;

  @GetMapping("/profile")
  public String profile() {
    List<String> profiles = Arrays.asList(env.getActiveProfiles());

    List<String> realProfiles = Arrays.asList("real", "real1", "real2");

    String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

    return profiles.stream()
        .filter(realProfiles::contains)
        .findAny()
        .orElse(defaultProfile);
  }
}
