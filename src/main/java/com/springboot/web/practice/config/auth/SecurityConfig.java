package com.springboot.web.practice.config.auth;

import com.springboot.web.practice.domain.user.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.config.auth
 * fileName         : SecurityConfig
 * author           : JYHwang
 * date             : 2022-03-22
 * description      : Spring Security WebSecurityConfig
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-22           JYHwang                 최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .authorizeRequests()
        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile")
        .permitAll()
        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
        .anyRequest().authenticated()
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .and()
        .oauth2Login()
        .userInfoEndpoint()
        .userService(customOAuth2UserService);
  }
}