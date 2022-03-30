package com.springboot.web.practice.domain.profile.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;


/**
 * <pre>
 * packageName      : com.springboot.web.practice.domain.profile.controller
 * fileName         : ProfileControllerUnitTest
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


class ProfileControllerUnitTest {

  @Test
  @DisplayName("real_profile 조회")
  void lookupRealProfile() {
    //given
    String expectedProfile = "real";
    MockEnvironment env = new MockEnvironment();
    env.addActiveProfile(expectedProfile);
    env.addActiveProfile("oauth");
    env.addActiveProfile("real-db");

    ProfileController controller = new ProfileController(env);

    //when
    String profile = controller.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfile);
  }

  @Test
  @DisplayName("first_profile 조회")
  void lookupFirstProfile() {
    //given
    String expectedProfile = "oauth";
    MockEnvironment env = new MockEnvironment();

    env.addActiveProfile(expectedProfile);
    env.addActiveProfile("real-db");

    ProfileController controller = new ProfileController(env);

    //when
    String profile = controller.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfile);
  }

  @Test
  @DisplayName("default_profile 조회")
  void lookupDefaultProfile() {
    //given
    String expectedProfile = "default";
    MockEnvironment env = new MockEnvironment();
    ProfileController controller = new ProfileController(env);

    //when
    String profile = controller.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfile);
  }
}