package com.springboot.web.practice.domain.index.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndexApiTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("메인 페이지 로딩")
  void loadIndex() {
    //when
    String body = this.restTemplate.getForObject("/", String.class);

    //then
    assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
  }
}
