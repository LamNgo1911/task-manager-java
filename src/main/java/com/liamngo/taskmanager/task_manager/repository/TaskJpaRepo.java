package com.liamngo.taskmanager.task_manager.repository;

import com.liamngo.taskmanager.task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepo extends JpaRepository<Task, Long> {

}
