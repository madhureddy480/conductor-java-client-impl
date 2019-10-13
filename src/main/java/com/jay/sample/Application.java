package com.jay.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.netflix.conductor.client.http.MetadataClient;
import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.http.WorkflowClient;
import com.netflix.conductor.client.task.WorkflowTaskCoordinator;
import com.netflix.conductor.client.worker.Worker;

@SpringBootApplication
public class Application {

	@Autowired
	Worker indentVerificationTaskWorker;
	
	@Autowired
	Worker addIndentsByTypeTaskWorker;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args ->{
			System.out.println(ctx.getBeanDefinitionNames());
			
			WorkflowTaskCoordinator.Builder builder = new WorkflowTaskCoordinator.Builder();
			
			Worker[] workers = new Worker[] {this.indentVerificationTaskWorker,this.addIndentsByTypeTaskWorker};
			
			WorkflowTaskCoordinator coordinator = builder
					                                    .withWorkers(workers)
					                                    .withTaskClient(this.initiateTaskClient())
					                                    .withThreadCount(1)
					                                    .build();
			coordinator.init();
			
		};
	}
	
	@Bean
	public TaskClient initiateTaskClient() {
		TaskClient taskClient = new TaskClient();
		taskClient.setRootURI("http://localhost:8080/api/");
		return taskClient;
	}
	
	@Bean
	public WorkflowClient initiateWorkflowClient() {
		WorkflowClient client = new WorkflowClient();
		client.setRootURI("http://localhost:8080/api/");
		return client;
	}
	
	@Bean
	public MetadataClient initiateMetadataClient() {
		MetadataClient client = new MetadataClient();
		client.setRootURI("http://localhost:8080/api/");
		return client;
	}
}
