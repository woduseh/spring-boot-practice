package com.springboot.web.practice.domain.post.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.springboot.web.practice.domain.post.dao.PostRepository;
import com.springboot.web.practice.domain.post.dto.PostSaveRequestDto;
import com.springboot.web.practice.domain.post.entity.Post;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostApiTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

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
}
