package com.jay.sample.conductor.taskworkers;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import com.netflix.conductor.common.metadata.tasks.TaskResult.Status;

@Service("indentVerificationTaskWorker")
public class IndentVerificationTaskWorker implements Worker{

	@Override
	public String getTaskDefName() {
		return "verify_if_idents_are_added";
	}

	@Override
	public TaskResult execute(Task task) {
		System.out.println("Task is being executed...");
		System.out.println("Input data for this Task is...");
		Map<String,Object> inputData = task.getInputData();
		for(String field: inputData.keySet()) {
			System.out.println("Filed\t"+field+", value\t"+inputData.get(field));
		}
		
		Object myBusinessLogicReturnEntity = myBusinessLogicGoesHereForThisTask(inputData);
		System.out.println(myBusinessLogicReturnEntity);
		
		//Now My Business Logic Execution is completed successfully.
		
		//So updating the task status in Conductor server as completed. 
		//To send a response back to Conductor server, I am creating a TaskResult object.
		TaskResult result = new TaskResult();
		result.setStatus(Status.COMPLETED);
		result.addOutputData("is_idents_added", myBusinessLogicReturnEntity);
		
		return result;
	}
	
	private Object myBusinessLogicGoesHereForThisTask(Map<String,Object> inputData) {
		return false;
	}

}
