package com.liamngo.taskmanager.task_manager.controllers;

import com.liamngo.taskmanager.task_manager.dto.TaskCreateDto;
import com.liamngo.taskmanager.task_manager.dto.TaskReadDto;
import com.liamngo.taskmanager.task_manager.dto.TaskUpdateDto;
import com.liamngo.taskmanager.task_manager.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskReadDto> createTask(@RequestBody TaskCreateDto taskCreateDto){
        TaskReadDto  taskReadDto = taskService.createTask(taskCreateDto);

        return ResponseEntity.status(201).body(taskReadDto);
    }

    @GetMapping
    public  ResponseEntity<List<TaskReadDto>> getAllTasks(){
        List<TaskReadDto> ListTaskReadDto = taskService.getAllTasks();

        return ResponseEntity.status(200).body(ListTaskReadDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskReadDto> getOneTask(@PathVariable Long id){
        TaskReadDto taskReadDto = taskService.getOneTask(id);

        return ResponseEntity.status(200).body(taskReadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask( @RequestBody TaskUpdateDto taskUpdateDto, @PathVariable Long id){
        taskService.updateTask(id, taskUpdateDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
