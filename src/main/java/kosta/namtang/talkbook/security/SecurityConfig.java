package kosta.namtang.talkbook.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kosta.namtang.talkbook.common.RoleCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@Order(1)
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 일단 비활성화
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
        http.sessionManagement() .maximumSessions(1) /* session 허용 갯수 */ .expiredUrl("/login") /* session 만료시 이동 페이지*/ .maxSessionsPreventsLogin(true); /* 동일한 사용자 로그인시 x, false 일 경우 기존 사용자 session 종료*/


        http.userDetailsService(simpleUserService);

        //http.rememberMe().key("namtang").userDetailsService(simpleUserService).tokenRepository(getJDBCRepository())
        //        .tokenValiditySeconds(60 * 60 * 24);
        //http.addFilterBefore(new AdminFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    private PersistentTokenRepository getJDBCRepository() {

        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build auth global....");
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(this.encoder());
    }

}
