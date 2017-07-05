package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

//@EnableZipkinServer
@SpringBootApplication
@EnableZipkinStreamServer
public class DemoZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoZipkinApplication.class, args);
	}
}
