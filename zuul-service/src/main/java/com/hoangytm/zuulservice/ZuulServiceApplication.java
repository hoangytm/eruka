package com.hoangytm.zuulservice;

import com.hoangytm.zuulservice.filter.ErrorFilter;
import com.hoangytm.zuulservice.filter.PostFilter;
import com.hoangytm.zuulservice.filter.PreFilter;
import com.hoangytm.zuulservice.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class, args);
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
}
