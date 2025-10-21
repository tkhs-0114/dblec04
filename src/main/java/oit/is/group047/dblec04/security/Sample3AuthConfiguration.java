package oit.is.group047.dblec04.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/sample4/**").authenticated() // /sample4/以下は認証済みであること
            .anyRequest().permitAll()) // 上記以外は全員アクセス可能
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions
                .sameOrigin()));
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // user1/1111,a/1111

    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$05$I7KdFtEClqdzBO5JR1z0r.8CxOE6ziJvYiVAPk14KEQ7E/ZAu8CaK").roles("user").build();
    UserDetails admin = User.withUsername("a")
        .password("{bcrypt}$2y$05$I7KdFtEClqdzBO5JR1z0r.8CxOE6ziJvYiVAPk14KEQ7E/ZAu8CaK").roles("admin").build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, admin);
  }

}
