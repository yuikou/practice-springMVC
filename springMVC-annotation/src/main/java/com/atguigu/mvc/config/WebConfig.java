package com.atguigu.mvc.config;

import com.atguigu.mvc.interceptor.TestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;


/**
 * 用來代替SpringMVC的配置文件:
 * 1. 掃描組件
 * 2. 配置view resolver
 * 3. 配置視圖控制器 view-controller
 * 4. 開放靜態資源處理 default-servlet-handler
 * 5. MVC的註解驅動
 * 6. 文件上傳解析器
 * 7. 攔截器
 * 8. 異常處理器
 */

//將當前class標示為一個配置類
@Configuration
//1. 掃描組件
@ComponentScan("com.atguigu.mvc")
//5. MVC的註解驅動
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    //4.開放靜態資源處理 default-servlet-handler
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //7. 攔截器
    public void addInterceptors(InterceptorRegistry registry) {
        TestInterceptor ti = new TestInterceptor();
        registry.addInterceptor(ti).addPathPatterns("/**");  // "/**"表示所有請求
    }

    //3.view-controller
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("hello");
    }



    //6. 文件上傳解析器
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

        return multipartResolver;
    }


    //8. 異常處理器
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException", "error");
        resolver.setExceptionMappings(properties);
        resolver.setExceptionAttribute("ex");

        resolvers.add(resolver);
    }

    //2.以下配置ThymeleafViewResolver
    //配置生成模板解析器
    @Bean
    public ITemplateResolver iTemplateResolver(){

        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
    }

    //生成模板引擎未模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine engine(ITemplateResolver iTemplateResolver){

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(iTemplateResolver);

        return engine;
    }

    //生成view-resolver
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){

        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setTemplateEngine(templateEngine);
        thymeleafViewResolver.setCharacterEncoding("UTF-8");

        return thymeleafViewResolver;
    }

}
