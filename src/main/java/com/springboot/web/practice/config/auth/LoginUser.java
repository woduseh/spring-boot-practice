package com.springboot.web.practice.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * packageName      : com.springboot.web.practice.config.auth
 * fileName         : LoginUser
 * author           : JYHwang
 * date             : 2022-03-22
 * description      : HandlerMethodArgumentResolver에서 사용자 정보를 가져오는데 사용하기 위한 마커 어노테이션
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-22           JYHwang                 최초 생성
 * </pre>
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
