package kosta.namtang.talkbook.security;

import kosta.namtang.talkbook.common.RoleCode;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private UserDetailsService userDetailService;

	@Autowired
    private DataSource dataSource;

	@Autowired
    private SimpleUserService simpleUserService;

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/static/**");
	}

//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	    System.out.println("configure(AuthenticationManagerBuilder auth)   call...................");
//	    auth.userDetailsService(userDetailService);
//	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	    // 일단 비활성화
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/tiles/**").permitAll()
                .antMatchers("/resources/**").permitAll().anyRequest().permitAll();
                //.antMatchers("/admin/**").hasRole("admin")
                //.antMatchers("/user/**").hasRole(RoleCode.Member.name());

        http.formLogin().loginPage("/guest/login");
        http.exceptionHandling().accessDeniedPage("/guest/register");
        http.logout().logoutUrl("/guest/logout").invalidateHttpSession(true);

        http.rememberMe().key("namtang").userDetailsService(simpleUserService).tokenRepository(getJDBCRepository())
                .tokenValiditySeconds(60 * 60 * 24);

//        http.formLogin()
//        .loginPage("/adminLoginForm")
//       // .loginProcessingUrl("/admin/adminloginCheck")
//        .defaultSuccessUrl("/", true)
//        .usernameParameter("username")
//        .passwordParameter("password");
//        http.exceptionHandling().accessDeniedPage("/");

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

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    log.info("build auth global....");
	    auth.userDetailsService(simpleUserService).passwordEncoder(this.passwordEncoder());
    }

    @SuppressWarnings("deprecation")
	@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
