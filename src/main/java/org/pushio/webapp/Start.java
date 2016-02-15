package org.pushio.webapp;


import java.util.List;

import org.pushio.webapp.support.Fastjson2HttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableRedisHttpSession
@ComponentScan("org.pushio.webapp")
@EntityScan("org.pushio.webapp.entity")
@EnableJpaRepositories("org.pushio.webapp.repository")
public class Start extends WebMvcConfigurerAdapter{

		
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Start.class);
	}
	
	 @Override
	 public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	  converters.add(Fastjson2HttpMessageConverter.makeFastjson2HttpMessageConverter());
//	  this.addDefaultHttpMessageConverters(converters);
	 }
	 
    public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	} 
    
    /**
     * 配置拦截器
     * @author lance
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
    	//registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/user/**");
	}
    
    /**
     * spring boot 定时任务
     */
    @Scheduled(cron="0 0 22 * * ?")
    public void doSth() {

    }
}