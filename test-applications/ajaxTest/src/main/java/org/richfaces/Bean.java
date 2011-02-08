package org.richfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bean implements Serializable {

	private String text;
	private CombinedDataModel tasks;
	private List<TaskImpl> taskList;

	public Bean() {
	}

	public String getText() {
		return text;
	}

	public void setText(String name) {
		this.text = name;
	}

	public CombinedDataModel getTasks() {
		if (taskList == null) {
			taskList = new ArrayList<TaskImpl>(1000);
			for(int i=0;i<1000;i++){
				TaskImpl task = new TaskImpl();
				task.setId("ID"+i);
				task.setAssignedTo("nobody");
				task.setExpanded(i%3==0);
				task.setName("Test task N "+i);
				ArrayList<SubTask> subtasks = new ArrayList<SubTask>(10);
				for(int j=0;j<10;j++){
					SubTask subTask = new SubTask();
					subTask.setId("SUD"+i+":"+j);
					subTask.setAssignedTo("guest");
					subTask.setName("Test subTask N "+j);
					subtasks.add(subTask);
				}
				task.setSubtasks(subtasks);
				taskList.add(task);
			}
		}

		return new CombinedDataModel(taskList);
	}

}
