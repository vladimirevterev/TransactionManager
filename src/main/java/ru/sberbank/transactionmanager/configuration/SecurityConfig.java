package ru.sberbank.transactionmanager.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import ru.sberbank.transactionmanager.common.dictionary.RoleDictionary;
import ru.sberbank.transactionmanager.configuration.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PAGE_URI = "/login";
    private static final String API_TRANSACTION_URL_PATTERN = "/api/transaction/**";
    private static final String API_USER_URL_PATTERN = "/api/user/**";

    private static final String[] BASE_INDEX_URL_PATTERN = {
            "/**",
            "/index"
    };

    private static final String[] SWAGGER_ENDPOINTS = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    private static final String[] H2_ENDPOINTS = {
            "/h2-console/**"
    };

    private AuthenticationEntryPoint authEntryPoint;

    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().authenticationEntryPoint(authEntryPoint);
        http.formLogin().permitAll();
        http.logout().permitAll();

        http.authorizeRequests()
                .antMatchers(LOGIN_PAGE_URI).permitAll()
                .antMatchers(SWAGGER_ENDPOINTS).hasAnyRole(RoleDictionary.ADMIN.toString(), RoleDictionary.USER.toString())
                .antMatchers(H2_ENDPOINTS).hasRole(RoleDictionary.ADMIN.toString())
                .antMatchers(API_TRANSACTION_URL_PATTERN).hasAnyRole(RoleDictionary.ADMIN.toString(), RoleDictionary.USER.toString())
                .antMatchers(HttpMethod.GET, API_USER_URL_PATTERN).hasAnyRole(RoleDictionary.ADMIN.toString(), RoleDictionary.USER.toString())
                .antMatchers(HttpMethod.PUT, API_USER_URL_PATTERN).hasRole(RoleDictionary.ADMIN.toString())
                .antMatchers(HttpMethod.POST, API_USER_URL_PATTERN).hasRole(RoleDictionary.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, API_USER_URL_PATTERN).hasRole(RoleDictionary.ADMIN.toString())
                .antMatchers(BASE_INDEX_URL_PATTERN).hasAnyRole(RoleDictionary.ADMIN.toString(), RoleDictionary.USER.toString())
                .anyRequest().authenticated();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
