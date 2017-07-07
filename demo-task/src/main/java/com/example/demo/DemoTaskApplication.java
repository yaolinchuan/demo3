package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;
import org.springframework.cloud.deployer.spi.core.RuntimeEnvironmentInfo;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.cloud.deployer.spi.task.TaskStatus;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.launcher.TaskLauncherSink;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
				System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 <<<<<<<<<<<<<");
			}catch (Exception e){

			}

		}
	}


	@Component
	public  class  MyTaskLauncher implements TaskLauncher{

		@Override
		public String launch(AppDeploymentRequest appDeploymentRequest) {
			return null;
		}

		@Override
		public void cancel(String s) {

		}

		@Override
		public TaskStatus status(String s) {
			return null;
		}

		@Override
		public void cleanup(String s) {

		}

		@Override
		public void destroy(String s) {

		}

		@Override
		public RuntimeEnvironmentInfo environmentInfo() {
			return null;
		}
	}
}
