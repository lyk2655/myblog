package com.linyk3.myblog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.linyk3.myblog.interceptor.ArticleClickInterceptor;
import com.linyk3.myblog.interceptor.LoginRequestInterceptor;
import com.linyk3.myblog.interceptor.PassportInterceptor;


//配置拦截器
@Component
public class WebConfiguration extends WebMvcConfigurerAdapter{
    @Autowired
    private PassportInterceptor passportInterceptor;

    @Autowired
    private LoginRequestInterceptor loginRequestInterceptor;

    @Autowired
    private ArticleClickInterceptor articleClickInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//拦截器可以拦截部分或所有请求
    	//先利用拦截器判断用户登录状态，再利用拦截器判断用户身份进行用户权限管理
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequestInterceptor).addPathPatterns("/create");
        registry.addInterceptor(articleClickInterceptor).addPathPatterns("/article/*");
        super.addInterceptors(registry);
    }
}
