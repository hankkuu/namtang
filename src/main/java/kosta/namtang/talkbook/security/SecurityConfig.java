//package kosta.namtang.talkbook.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UserDetailsService userDetailService;
//
//	@Override
//	public void configure(WebSecurity web) {
//		web.ignoring().antMatchers("/static/**");
//	}
//
//
//	  @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	       System.out.println("configure(AuthenticationManagerBuilder auth)   call...................");
//	        auth.userDetailsService(userDetailService);
//	    }
//
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//        .antMatchers("/*").permitAll()
//        .antMatchers("/admin/dashBoard").hasRole("admin");
//
//        http.formLogin()
//        .loginPage("/adminLoginForm")
//       // .loginProcessingUrl("/admin/adminloginCheck")
//        .defaultSuccessUrl("/", true)
//        .usernameParameter("username")
//        .passwordParameter("password");
//        http.exceptionHandling().accessDeniedPage("/");
//
//    }
//    @SuppressWarnings("deprecation")
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
