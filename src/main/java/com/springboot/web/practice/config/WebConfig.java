package com.springboot.web.practice.config;

import com.springboot.web.practice.config.auth.LoginUserArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.config.auth
 * fileName         : WebConfig
 * author           : JYHwang
 * date             : 2022-03-22
 * description      : WebConfig 파일
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-22           JYHwang                 최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final LoginUserArgumentResolver loginUserArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(loginUserArgumentResolver);
  }


}
