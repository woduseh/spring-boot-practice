package com.springboot.web.practice.domain.post.dao;

import com.springboot.web.practice.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsJpaRepository extends JpaRepository<Posts, Long> {

}
