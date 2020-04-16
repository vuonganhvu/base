package com.anhvv.base.security;

import com.anhvv.base.security.filter.JWTAuthenticationProcessingFilter;
import com.anhvv.base.security.filter.UsernameAndPasswordAuthenticationProcessingFilter;
import com.anhvv.base.security.handler.UsernameAndPasswordAuthenticationSuccessHandler;
import com.anhvv.base.security.provider.JWTAuthenticationProvider;
import com.anhvv.base.security.provider.UsernameAndPasswordAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class WebAppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final UsernameAndPasswordAuthenticationProvider usernameAndPasswordAuthenticationProvider;
    private final UsernameAndPasswordAuthenticationSuccessHandler usernameAndPasswordAuthenticationSuccessHandler;
    private final JWTAuthenticationProvider jwtAuthenticationProvider;


    public WebAppSecurityConfigurer(UsernameAndPasswordAuthenticationProvider usernameAndPasswordAuthenticationProvider,
                                    UsernameAndPasswordAuthenticationSuccessHandler usernameAndPasswordAuthenticationSuccessHandler,
                                    JWTAuthenticationProvider jwtAuthenticationProvider) {
        this.usernameAndPasswordAuthenticationProvider = usernameAndPasswordAuthenticationProvider;
        this.usernameAndPasswordAuthenticationSuccessHandler = usernameAndPasswordAuthenticationSuccessHandler;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    private UsernameAndPasswordAuthenticationProcessingFilter usernameAndPasswordAuthenticationProcessingFilter() throws Exception {
        UsernameAndPasswordAuthenticationProcessingFilter filter = new UsernameAndPasswordAuthenticationProcessingFilter("/login", new ObjectMapper());
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(usernameAndPasswordAuthenticationSuccessHandler);
        return filter;
    }

    private JWTAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() throws Exception {
        JWTAuthenticationProcessingFilter filter = new JWTAuthenticationProcessingFilter("/api/**");
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .cors(configCORS())
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(usernameAndPasswordAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.
                ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.authenticationProvider(usernameAndPasswordAuthenticationProvider);
        builder.authenticationProvider(jwtAuthenticationProvider);
    }

//    @Bean(name="myAuthenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }


    private Customizer<CorsConfigurer<HttpSecurity>> configCORS(){

        return null;
    }

}
