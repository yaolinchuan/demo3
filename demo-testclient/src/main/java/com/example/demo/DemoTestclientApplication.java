package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
public class DemoTestclientApplication {

	/**
	 * rabbon 默认编码 ISO8859-1
	 * @return
	 */
//	@Bean
//	@LoadBalanced
//	RestTemplate restTemplate() {
//		StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//		RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
//		return restTemplate;
//	}

	public static void main(String[] args) {
		SpringApplication.run(DemoTestclientApplication.class, args);
	}
}
