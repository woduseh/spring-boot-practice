package com.springboot.web.practice.config.auth;

import com.springboot.web.practice.config.auth.dto.SessionUser;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * <pre>
 * packageName      : com.springboot.web.practice.config.auth
 * fileName         : LoginUserArgumentResolver
 * author           : JYHwang
 * date             : 2022-03-22
 * description      : httpSession.getAttribute("user") 라는 반복되는 코드를 파라미터로 넘기도록 하는 코드
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-22           JYHwang                 최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

  private final HttpSession httpSession;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;

    boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

    return isLoginUserAnnotation && isUserClass;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    return httpSession.getAttribute("user");
  }
}
