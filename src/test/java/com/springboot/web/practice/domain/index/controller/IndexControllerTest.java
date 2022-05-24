package com.springboot.web.practice.domain.index.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.post.dao.PostsRepository;
import com.springboot.web.practice.domain.post.entity.Posts;
import com.springboot.web.practice.domain.user.dao.UserRepository;
import com.springboot.web.practice.domain.user.entity.Role;
import com.springboot.web.practice.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@DisplayName("index Controller 기능 테스트")
@RequiredArgsConstructor
@TestConstructor(autowireMode = AutowireMode.ALL)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

  @LocalServerPort
  private int port;
  private final PostsRepository postRepository;
  private final UserRepository userRepository;
  private final WebApplicationContext context;
  private MockMvc mvc;
  protected MockHttpSession session;
  private final TestRestTemplate restTemplate;

  @BeforeEach
  public void setup() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();

    User loginUser = userRepository.save(User.builder()
        .name("JY")
        .email("jy@gmail.com")
        .role(Role.USER)
        .build());
    session = new MockHttpSession();
    session.setAttribute("user", new SessionUser(loginUser));
  }

  @AfterEach
  public void tearDown() {
    postRepository.deleteAll();
  }

  @Test
  @DisplayName("메인 페이지 로딩")
  void loadIndex() {
    //when
    String body = this.restTemplate.getForObject("/", String.class);

    //then
    assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
  }

  @Test
  @WithMockUser(roles = "USER")
  @DisplayName("Posts 갱신 페이지 접근 권한 테스트")
  void updatePostAuthorityCheck() throws Exception {
    //given
    User writeUser = userRepository.save(User.builder()
        .name("JY")
        .email("jy@gmail.com")
        .role(Role.USER)
        .build());

    Posts savedPost = postRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author(writeUser)
        .build());

    Long postId = savedPost.getId();

    String url = "http://localhost:" + port + "/api/posts/" + postId;

    //when - then
    mvc.perform(patch(url)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
  }
}
