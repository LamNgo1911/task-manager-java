package com.liamngo.taskmanager.task_manager.services;

import com.liamngo.taskmanager.task_manager.dto.TaskCreateDto;
import com.liamngo.taskmanager.task_manager.dto.TaskReadDto;
import com.liamngo.taskmanager.task_manager.dto.TaskUpdateDto;
import com.liamngo.taskmanager.task_manager.exception.TaskNotFoundException;
import com.liamngo.taskmanager.task_manager.mapper.TaskMapper;
import com.liamngo.taskmanager.task_manager.model.Task;
import com.liamngo.taskmanager.task_manager.repository.TaskJpaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final TaskJpaRepo taskJpaRepo;
    private final TaskMapper taskMapper;

    public TaskService(TaskJpaRepo taskJpaRepo, TaskMapper taskMapper){
        this.taskJpaRepo = taskJpaRepo;
        this.taskMapper = taskMapper;
    }

    public TaskReadDto createTask(TaskCreateDto taskCreateDto) {
        try {
            Task newTask = taskMapper.toTask(taskCreateDto);
            taskJpaRepo.save(newTask);
            return taskMapper.toTaskReadDto(newTask);
        } catch (Exception e) {
            logger.error("Error updating task: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public List<TaskReadDto> getAllTasks() {
        try {
            return taskMapper.toListTaskReadDto(taskJpaRepo.findAll());
        } catch (Exception e) {
            logger.error("Error updating task: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public TaskReadDto getOneTask(Long id) {
        try {
            return taskMapper.toTaskReadDto(taskJpaRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id)));
        } catch (Exception e) {logger.error("Error updating task: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void updateTask(Long id, TaskUpdateDto taskUpdateDto) {
        try {
            Task task = taskJpaRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

            taskMapper.updateTaskFromDto(taskUpdateDto, task);

            taskJpaRepo.save(task);
        } catch (Exception e) {
            logger.error("Error updating task: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void deleteTask(Long id) {
        try {
            taskJpaRepo.deleteById(id);
        } catch (Exception e) {logger.error("Error updating task: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
