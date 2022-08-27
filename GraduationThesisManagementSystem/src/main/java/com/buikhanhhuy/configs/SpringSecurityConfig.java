package com.buikhanhhuy.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.buikhanhhuy.repository", "com.buikhanhhuy.service"})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean("passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password");
        http.formLogin().successHandler(this.authenticationSuccessHandler).failureUrl("/login?error");
        http.logout().logoutSuccessHandler(this.logoutSuccessHandler);

        http.exceptionHandling().accessDeniedPage("/login/?accessDenied");

        http.authorizeRequests().antMatchers("/public/**").permitAll()
                .antMatchers("/", "/thesis/**", "/api/theses/**")
                .hasAnyAuthority("STUDENT")
                .antMatchers("/admin/")
                .hasAnyAuthority("ADMIN" ,"MINISTRY", "LECTURER")
                .antMatchers("/admin/**/councils-detail/**")
                .hasAuthority("LECTURER")
                .antMatchers(HttpMethod.GET, "/admin/api/departments/**", "/admin/api/majors/**")
                .hasAnyAuthority("ADMIN", "MINISTRY")
                .antMatchers("/admin/stats/**")
                .hasAnyAuthority("ADMIN", "MINISTRY")
                .antMatchers(HttpMethod.PATCH, "/admin/api/notifications-user/**")
                .hasAnyAuthority("STUDENT", "LECTURER", "MINISTRY", "ADMIN")
                .antMatchers("/admin/roles/",
                        "/admin/manages/",
                        "/admin/api/roles/**",
                        "/admin/api/manages/**",
                        "/admin/api/departments/**",
                        "/admin/api/majors/**",
                        "/admin/api/school-years/**",
                        "/admin/api/positions/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/admin/**")
                .hasAnyAuthority("ADMIN", "MINISTRY", "LECTURER")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();

        http.csrf().disable();
    }
}
