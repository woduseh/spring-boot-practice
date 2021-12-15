package com.springboot.web.practice.domain.post.dao;

import com.springboot.web.practice.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
