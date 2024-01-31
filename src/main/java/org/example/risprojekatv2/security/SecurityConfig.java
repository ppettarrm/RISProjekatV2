package org.example.risprojekatv2.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/addPost", "/addPost.jsp", "/postAction/**", "/saved.jsp", "/saved/**", "/follow/**",
                          "/chat/**", "/chat.jsp").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/register.jsp", "/home.jsp","/utils/**", "/css/**", "/auth/**", "/home", "/post.jsp",
                        "/post/**", "/profile.jsp", "/profile/**", "/search.jsp", "/search/**", "/forgotPassword.jsp").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                .loginPage("/login.jsp").permitAll()
                .loginProcessingUrl("/auth/login")
                                .defaultSuccessUrl("/home")
                                .failureUrl("/auth/loginPage")

                )
                .exceptionHandling(handling -> handling.accessDeniedPage("/auth/loginPage"))
                .httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
