package com.liamngo.taskmanager.task_manager;

import com.liamngo.taskmanager.task_manager.controllers.TaskController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskManagerApplicationTests {
	@Autowired
	private TaskController taskController;

	@Test
	void contextLoads() {
		assertThat(taskController).isNotNull();
	}

}
