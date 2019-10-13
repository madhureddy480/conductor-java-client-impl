package com.jay.sample.conductor.taskworkers;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import com.netflix.conductor.common.metadata.tasks.TaskResult.Status;

@Service("addIndentsByTypeTaskWorker")
public class AddIndentsByTypeTaskWorker implements Worker{

	@Override
	public String getTaskDefName() {
		return "add_idents";
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
		
		
		return result;
	}

	
	private Object myBusinessLogicGoesHereForThisTask(Map<String,Object> inputData) {
		return true;
	}

}
