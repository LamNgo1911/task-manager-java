package com.liamngo.taskmanager.task_manager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liamngo.taskmanager.task_manager.dto.TaskCreateDto;
import com.liamngo.taskmanager.task_manager.dto.TaskReadDto;
import com.liamngo.taskmanager.task_manager.services.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    @Test
    void ShouldCreateTask_ValidInput() throws Exception {
        // Arrange
        TaskCreateDto example = new TaskCreateDto();
        example.setTitle("1");
        example.setDescription("2");
        example.setDeadline(Date.from(Instant.now()));

        TaskReadDto result = new TaskReadDto();
        result.setId(1L);
        result.setTitle("1");
        result.setDescription("2");
        result.setDeadline(example.getDeadline());

        when(taskService.createTask(any(TaskCreateDto.class))).thenReturn(result);

        // Act & Assert
        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(example)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("1"))
                .andExpect(jsonPath("$.description").value("2"));
    }

    @Test
    void ShouldThrowBadRequest() throws Exception {
        // Arrange
        TaskCreateDto example = new TaskCreateDto();
        example.setTitle("1");
        example.setDeadline(Date.from(Instant.now()));

        // Act & Assert
        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(example)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ShouldGet_AllTasks() throws Exception {

    }
}
