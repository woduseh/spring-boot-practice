package com.springboot.web.practice.domain.post.dao;

import com.springboot.web.practice.domain.post.entity.Posts;
import java.util.List;

public interface PostsCustomRepository {

  List<Posts> findAllDesc();
}
