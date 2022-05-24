package com.springboot.web.practice.domain.math.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.math.dao.MathRepository;
import com.springboot.web.practice.domain.math.dto.request.MathSaveRequestDto;
import com.springboot.web.practice.domain.math.entity.MathExpression;
import com.springboot.web.practice.domain.user.dao.UserRepository;
import com.springboot.web.practice.domain.user.entity.Role;
import com.springboot.web.practice.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = AutowireMode.ALL)
@DisplayName("수식 검증 관련 API 기능 테스트")
@RequiredArgsConstructor
class MathApiTest {

  @LocalServerPort
  private int port;
  private final MathRepository mathRepository;
  private final UserRepository userRepository;
  private final WebApplicationContext context;
  private MockMvc mvc;
  protected MockHttpSession session;

  @BeforeEach
  public void setup() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();

    User user = userRepository.save(User.builder()
        .name("JY")
        .email("jy@gmail.com")
        .role(Role.USER)
        .build());

    session = new MockHttpSession();
    session.setAttribute("user", new SessionUser(user));
  }

  @AfterEach
  public void tearDown() {
    mathRepository.deleteAll();
  }

  @Test
  @WithMockUser(roles = "USER")
  @DisplayName("수식 채점 결과 저장")
  void save() throws Exception {
    // given
    String expression = "12 + 3 = 14";
    String correctAnswer = "15";

    String url = "http://localhost:" + port + "/api/maths/save";

    MathSaveRequestDto requestDto = MathSaveRequestDto.builder()
        .expression(expression)
        .build();

    // when
    mvc.perform(post(url)
            .session(session)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(requestDto)))
        .andExpect(status().isOk());

    List<MathExpression> all = mathRepository.findAll();

    // then
    assertThat(all.get(0).getCorrectAnswer()).isEqualTo(correctAnswer);
    assertThat(all.get(0).getExpression()).isEqualTo(expression);
  }

  @Test
  @WithMockUser(roles = "USER")
  @DisplayName("특정 id의 채점 히스토리 찾기")
  void findHistory() throws Exception {
    // given
    User user = userRepository.save(User.builder()
        .name("JY")
        .email("jy@gmail.com")
        .role(Role.USER)
        .build());

    Long userId = user.getId();
    String url = "http://localhost:" + port + "/api/maths/" + userId;

    String expressionOne = "1 + 1 = 3";
    String correctAnswerOne = "2";

    String expressionTwo = "1 + 1 = 2";
    String correctAnswerTwo = "";

    MathExpression mathExpressionOne = MathExpression.builder()
        .expression(expressionOne)
        .correctAnswer(correctAnswerOne)
        .user(user)
        .build();

    MathExpression mathExpressionTwo = MathExpression.builder()
        .expression(expressionTwo)
        .correctAnswer(correctAnswerTwo)
        .user(user)
        .build();

    mathRepository.save(mathExpressionOne);
    mathRepository.save(mathExpressionTwo);

    // when
    MvcResult result = mvc.perform(get(url)
            .session(session)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    // then
    assertTrue(result.getResponse().getContentAsString().contains(expressionOne));
  }
}
