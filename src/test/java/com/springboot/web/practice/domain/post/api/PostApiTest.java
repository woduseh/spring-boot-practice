package com.springboot.web.practice.domain.post.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.springboot.web.practice.domain.post.dao.PostRepository;
import com.springboot.web.practice.domain.post.dto.request.PostUpdateRequestDto;
import com.springboot.web.practice.domain.post.dto.response.PostSaveRequestDto;
import com.springboot.web.practice.domain.post.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
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
public class PostApiTest {

  @LocalServerPort
  private int port;

  ClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();

  RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

  @Autowired
  private PostRepository postRepository;

  @AfterEach
  public void tearDown() {
    postRepository.deleteAll();
  }

  @Test
  public void savePost() {
    String title = "title";
    String content = "content";

    PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
        .title(title)
        .content(content)
        .author("Yeon")
        .build();

    String url = "http://localhost:" + port + "/api/post";

    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Post> all = postRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title);
    assertThat(all.get(0).getContent()).isEqualTo(content);
  }

  @Test
  public void updatePost() throws Exception {
    //given
    Post savedPost = postRepository.save(Post.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

    Long updateId = savedPost.getId();
    String expectedTitle = "title2";
    String expectedContent = "content2";

    PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
        .title(expectedTitle)
        .content(expectedContent)
        .build();

    String url = "http://localhost:" + port + "/api/post/" + updateId;

    HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

    //when
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH,
        requestEntity, Long.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Post> all = postRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
  }

  @Test
  public void checkBaseTimeEntity() {
    //given
    LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
    postRepository.save(Post.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());
    //when
    List<Post> postsList = postRepository.findAll();

    //then
    Post post = postsList.get(0);

    System.out.println(">>>>>>>>> createDate=" + post.getCreatedDate() + ", modifiedDate="
        + post.getModifiedDate());

    assertThat(post.getCreatedDate()).isAfter(now);
    assertThat(post.getModifiedDate()).isAfter(now);
  }
}
