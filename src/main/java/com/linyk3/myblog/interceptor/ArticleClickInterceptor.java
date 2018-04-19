package com.linyk3.myblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.linyk3.myblog.model.HostHolder;
import com.linyk3.myblog.service.JedisService;
import com.linyk3.myblog.util.RedisKeyUntil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//文章操作拦截器 "/article/*"
@Component
public class ArticleClickInterceptor implements HandlerInterceptor {
    @Autowired
    private JedisService jedisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
    	//利用Spring拦截器拦截多有文章页的请求，并在Redis中修改相应文章页的点击量，用于热门文章排序
        String uri = httpServletRequest.getServletPath().split("/")[2];
        String uriKey = RedisKeyUntil.getClickCountKey(uri);
        jedisService.zincrby("hotArticles",uriKey);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
