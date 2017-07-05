package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTask
@EnableBatchProcessing
@EnableTaskLauncher
public class DemoTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTaskApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new HelloWorldCommandLineRunner();
	}
	public  static  class HelloWorldCommandLineRunner implements CommandLineRunner {
		@Override
		public void run(String... strings) {
			try {

				System.out.println("Hello World！");
				Thread.sleep(60000);
			}catch (Exception e){

			}

		}
	}
}
