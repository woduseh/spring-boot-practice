package com.springboot.web.practice.domain.post.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.springboot.web.practice.domain.post.entity.Post;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest {

  @Autowired
  PostRepository postRepository;

  @AfterEach
  public void cleanup() {
    postRepository.deleteAll();
  }

  @Test
  public void saveAndReadPost() {
    //given
    String title = "테스트 게시글";
    String content = "테스트 본문";

    postRepository.save(Post.builder()
        .title(title)
        .content(content)
        .author("jojoldu@gmail.com")
        .build());

    //when
    List<Post> postList = postRepository.findAll();

    //then
    Post post = postList.get(0);
    assertThat(post.getTitle()).isEqualTo(title);
    assertThat(post.getContent()).isEqualTo(content);
  }
}
