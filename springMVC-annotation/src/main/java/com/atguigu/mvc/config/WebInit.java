package com.atguigu.mvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

//Web工程的初始化類, 用來代替web.xml
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定Spring的配置類
     * @return 配置類
     */

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     * 指定SpringMVC的配置類
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 指定DispatcherServlet的映射規則(url-pattern)
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 註冊filter
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {

        // 以下三種寫法都可以
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true,true);
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceResponseEncoding(true);
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter};
    }
}
