package com.example.demo;

import com.example.demo.apigateway.config.FilterConfiguration;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableHystrix
@EnableDiscoveryClient
@EnableConfigurationProperties(FilterConfiguration.class)
public class DemoApigatewayApplication {

	/**
	 * 动态路由
	 * @return
	 */
	@Bean
	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties() {
		return new ZuulProperties();
	}

	/**
	 * 动态过滤器
	 * @param filterConfiguration
	 * @return
	 */
	@Bean
	public FilterLoader filterLoader(FilterConfiguration filterConfiguration){
	    FilterLoader filterLoader= FilterLoader.getInstance();
	    filterLoader.setCompiler(new GroovyCompiler());
	    try {
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			FilterFileManager.init(
				filterConfiguration.getInterval(),

					filterConfiguration.getRoot()+"/post",
					filterConfiguration.getRoot()+"/pre",
					filterConfiguration.getRoot()+"/route",
					filterConfiguration.getRoot()+"/error"
			);
		}catch (Exception e){
	    	throw new RuntimeException(e);
		}
		return filterLoader;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApigatewayApplication.class, args);
	}
}
