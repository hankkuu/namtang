package kosta.namtang.talkbook.security;

import kosta.namtang.talkbook.security.intercepter.LoginCheckInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/login");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
