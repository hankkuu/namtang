package kosta.namtang.talkbook.security;

import kosta.namtang.talkbook.common.RoleCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


// 미사용
@Slf4j
@Configuration
@EnableWebSecurity
@Order(2)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole(RoleCode.Member.name());

        http.formLogin().loginPage("/login").defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and().exceptionHandling().accessDeniedPage("/");


        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
        http.userDetailsService(userDetailService);


        //http.addFilterBefore(new AdminFilter(), UsernamePasswordAuthenticationFilter.class);

        // 강은택 원본 내용
//        http.formLogin()
//        .loginPage("/adminLoginForm")
//        // .loginProcessingUrl("/admin/adminloginCheck")
//        .defaultSuccessUrl("/", true)
//        .usernameParameter("username")
//        .passwordParameter("password");
//        http.exceptionHandling().accessDeniedPage("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("configure(AuthenticationManagerBuilder auth)   call...................");
        auth.userDetailsService(userDetailService);
    }

}
