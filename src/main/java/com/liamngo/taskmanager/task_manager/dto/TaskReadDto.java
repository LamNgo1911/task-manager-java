package com.liamngo.taskmanager.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskReadDto {
    private Long id;
    private String title;
    private String description;
    private Date deadline;
}
