package com.springboot.web.practice.domain.post.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends PostsJpaRepository, PostsCustomRepository {

}
