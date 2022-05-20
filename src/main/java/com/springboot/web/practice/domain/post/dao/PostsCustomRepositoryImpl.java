package com.springboot.web.practice.domain.post.dao;

import static com.springboot.web.practice.domain.post.entity.QPosts.posts;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.web.practice.domain.post.entity.Posts;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PostsCustomRepositoryImpl implements PostsCustomRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public PostsCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<Posts> findAllDesc() {
    return jpaQueryFactory.selectFrom(posts)
        .fetch();
  }
}
