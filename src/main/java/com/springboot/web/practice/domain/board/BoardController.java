package com.springboot.web.practice.domain.board;

import com.springboot.web.practice.config.auth.LoginUser;
import com.springboot.web.practice.config.auth.dto.SessionUser;
import com.springboot.web.practice.domain.post.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.domain.home
 * fileName         : BoardController
 * author           : JYHwang
 * date             : 2022-03-28
 * description      :
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-28           JYHwang                 최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/board")
public class BoardController {

  private final PostsService postsService;

  @GetMapping()
  public String board(Model model, @LoginUser SessionUser user) {
    model.addAttribute("posts", postsService.findAllDesc());

    if (user != null) {
      model.addAttribute("loginUserName", user.getName());
    }

    return "board";
  }
}
