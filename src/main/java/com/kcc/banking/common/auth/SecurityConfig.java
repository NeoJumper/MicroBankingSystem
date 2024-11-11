package com.kcc.banking.common.auth;

import com.kcc.banking.common.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Slf4j
public class SecurityConfig {


    private static final String[] AUTH_WHITELIST = {
            "/page/error/**",
            "/page/anonymous/**",
            "/api/anonymous/**",
            "/resources/**",
            "/WEB-INF/views/**",
            //"/page/auth/login-form",
            //"/api/auth/**",
    };
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true); // 이중 슬래시 허용
        return firewall;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .requestMatchers("/api/manager/**").hasAnyRole("MANAGER")
                                .requestMatchers("/page/manager/**").hasAnyRole("MANAGER")
                                .requestMatchers("/api/employee/**").hasAnyRole("EMPLOYEE")
                                .requestMatchers("/page/employee/**").hasAnyRole("EMPLOYEE")
                                .requestMatchers("/api/common/**").hasAnyRole("EMPLOYEE", "MANAGER")
                                .requestMatchers("/page/common/**").hasAnyRole("EMPLOYEE", "MANAGER")
                                .requestMatchers("/auth/login").permitAll()
                                .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .formLogin(form -> form.loginPage("/page/anonymous/login-form")
                        .loginProcessingUrl("/api/anonymous/login")
                        .successHandler(new CustomAuthenticationSuccessHandler() )
                        .failureHandler(authenticationFailureHandler())
                ).logout(logout ->
                        logout.logoutUrl("/api/common/logout"));
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.accessDeniedHandler(new CustomAccessDeniedHandler())
                );

        return http.build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {

            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException, ServletException, IOException {

                String errorMessage = getExceptionMessage(exception);

                errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);  //한글 인코딩
                setDefaultFailureUrl("/page/auth/login-form?error=true&exception=" + errorMessage);
                System.out.println(errorMessage);
                super.onAuthenticationFailure(request, response, exception);
            }

            private String getExceptionMessage(AuthenticationException exception) {
                if (exception instanceof BadCredentialsException)  {
                    return  "아이디 또는 비밀번호가 일치하지 않습니다.";
                } else if (exception instanceof UsernameNotFoundException){
                    return "계정정보가 없습니다.";
                } else {
                    return "잘못된 로그인 시도입니다. 아이디 또는 비밀번호를 확인해 주세요.";
                }
            }
        };
    }


    /**
     * 1. 권한이 없는 페이지에 접근할 경우 403 Error페이지로 이동(화면상에 표시되지만 권한에 따른 접근제어가 필요할 때)
     * 2. 권한이 없는 API에 접근할 경우 Status는 403 Body는 "접근 권한이 없습니다." (인증 정보를 가지고 외부에서 접근하거나 잘못된 코드로 인한 내부 요청이 일어날 때 )
     * 3. referer값을 확인하여 null값일 경우 접근제한(url직접 입력을 통한 접근을 제어) - 추후 도입 예정
     * 4. referer값이 외부 사이트일 경우(외부 링크를 통한 접근) - 추후 도입 예정
     */
    @Component
    public class CustomAccessDeniedHandler implements AccessDeniedHandler {
        @Override
        public void handle(HttpServletRequest request,
                           HttpServletResponse response,
                           AccessDeniedException accessDeniedException) throws IOException {
            Collection<? extends GrantedAuthority> authorities =SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String s = request.getRequestURI().split("/")[1];

            String referer = request.getHeader("Referer");


            if(s.equals("page"))
                response.sendRedirect("/page/error/error-403");
            else if(s.equals("api")){
                // 응답 형식 설정
                response.setContentType("application/json; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden 상태 코드

                // JSON 데이터 생성
                String jsonResponse = String.format(
                        String.valueOf("접근 권한이 없습니다.")
                );

                // JSON 응답 작성
                PrintWriter out = response.getWriter();
                out.print(jsonResponse);
                out.flush();
            }
        }
    }

    @Component
    public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            String redirectUrl = "/default";

            // 사용자의 권한에 따라 리다이렉트 URL 설정
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"))) {
                redirectUrl = "/page/employee/dashboard";
            } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_MANAGER"))) {
                redirectUrl = "/page/manager/dashboard";
            }

            response.sendRedirect(redirectUrl);
        }
    }

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

}
