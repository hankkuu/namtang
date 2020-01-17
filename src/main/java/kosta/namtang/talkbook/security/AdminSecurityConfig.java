//package kosta.namtang.talkbook.security;
//
//import kosta.namtang.talkbook.common.RoleCode;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Slf4j
//@Configuration
//@EnableWebSecurity
//@Order(2)
//public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //@Autowired
//    //private UserDetailsServiceImpl userDetailService;
//
//    @Autowired
//    private SimpleUserService simpleUserService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("configure(AuthenticationManagerBuilder auth)   call...................");
//        auth.userDetailsService(simpleUserService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //super.configure(http);
//
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .antMatchers("/login", "/register", "/adminLoginForm").permitAll()
//                .antMatchers("/admin/**").hasRole("admin");
//
//        http.formLogin()
//                .loginPage("/adminLoginForm").failureUrl("/adminLoginForm?error=loginError")
//                .defaultSuccessUrl("/admin/dashBoard");
//        // .loginProcessingUrl("/admin/adminloginCheck")
//        //.defaultSuccessUrl("/admin/dashBoard", true)
//        //.usernameParameter("username")
//        //.passwordParameter("password");
//        http.exceptionHandling().accessDeniedPage("/register");
//
//        http.userDetailsService(simpleUserService);
//
////        http.formLogin().loginPage("/login").successHandler(new LoginSuccessHandler());
////        http.exceptionHandling().accessDeniedPage("/register");
////
//
//        //http.addFilterBefore(new AdminFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    }
//}
