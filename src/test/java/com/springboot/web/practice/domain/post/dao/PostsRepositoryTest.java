package com.springboot.web.practice.domain.post.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.springboot.web.practice.domain.post.entity.Posts;
import com.springboot.web.practice.domain.user.dao.UserRepository;
import com.springboot.web.practice.domain.user.entity.Role;
import com.springboot.web.practice.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {

  @Autowired
  PostsRepository postRepository;

  @Autowired
  UserRepository userRepository;

  @AfterEach
  public void cleanup() {
    postRepository.deleteAll();
  }

  @Test
  void saveAndReadPost() {
    //given
    String title = "테스트 게시글";
    String content = "테스트 본문";
    User user = userRepository.save(User.builder()
        .name("JY")
        .email("jy@gmail.com")
        .role(Role.USER)
        .build());

    postRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author(user)
        .build());

    //when
    List<Posts> postList = postRepository.findAll();

    //then
    Posts post = postList.get(0);
    assertThat(post.getTitle()).isEqualTo(title);
    assertThat(post.getContent()).isEqualTo(content);
  }
}
