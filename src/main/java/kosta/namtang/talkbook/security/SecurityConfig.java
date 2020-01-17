package kosta.namtang.talkbook.security;

import kosta.namtang.talkbook.common.RoleCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableWebSecurity
@Order(1)
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SimpleUserService simpleUserService;

//    @Override
//    public void configure(WebSecurity web) {
//        //web.ignoring().antMatchers("/**");
//    }

	@Override
	public void configure(WebSecurity web) {

	    web.ignoring().antMatchers("/static/**");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 일단 비활성화
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login", "/register", "/adminLoginForm").permitAll()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/**").hasRole(RoleCode.Admin.name())
                .antMatchers("/user/**").hasRole(RoleCode.Admin.name())
                .antMatchers("/user/**").hasRole(RoleCode.Member.name());

        http.formLogin().loginPage("/login").successHandler(new LoginSuccessHandler());

        http.exceptionHandling().accessDeniedPage("/register");
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
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
        auth.userDetailsService(simpleUserService).passwordEncoder(this.encoder());
    }

}
