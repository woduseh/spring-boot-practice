package com.springboot.web.practice.domain.post.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.springboot.web.practice.domain.post.dao.PostsRepository;
import com.springboot.web.practice.domain.post.dto.request.PostsUpdateRequestDto;
import com.springboot.web.practice.domain.post.dto.response.PostsSaveRequestDto;
import com.springboot.web.practice.domain.post.entity.Posts;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Posts 관련 API 기능 테스트")
class PostsApiTest {

  @LocalServerPort
  private int port;

  ClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();

  RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

  @Autowired
  private PostsRepository postRepository;

  @AfterEach
  public void tearDown() {
    postRepository.deleteAll();
  }

  @Test
  @DisplayName("Posts 저장")
  void savePost() {
    String title = "title";
    String content = "content";

    PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
        .title(title)
        .content(content)
        .author("Yeon")
        .build();

    String url = "http://localhost:" + port + "/api/posts";

    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isPositive();

    List<Posts> all = postRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title);
    assertThat(all.get(0).getContent()).isEqualTo(content);
  }

  @Test
  @DisplayName("JPA 영속성 컨텍스트를 이용한 Posts 갱신")
  void updatePost() {
    //given
    Posts savedPost = postRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

    Long updateId = savedPost.getId();
    String expectedTitle = "title2";
    String expectedContent = "content2";

    PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
        .title(expectedTitle)
        .content(expectedContent)
        .build();

    String url = "http://localhost:" + port + "/api/posts/" + updateId;

    HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

    //when
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH,
        requestEntity, Long.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isPositive();

    List<Posts> all = postRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
  }

  @Test
  @DisplayName("Post에서 extend한 BaseTimeEntity가 잘 작동되는지 체크")
  void checkBaseTimeEntity() {
    //given
    LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
    postRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());
    //when
    List<Posts> postsList = postRepository.findAll();

    //then
    Posts post = postsList.get(0);

    System.out.println(">>>>>>>>> createDate=" + post.getCreatedDate() + ", modifiedDate="
        + post.getModifiedDate());

    assertThat(post.getCreatedDate()).isAfter(now);
    assertThat(post.getModifiedDate()).isAfter(now);
  }
}
