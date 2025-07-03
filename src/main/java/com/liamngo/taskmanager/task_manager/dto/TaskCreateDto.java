package com.liamngo.taskmanager.task_manager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDto {
    @NotNull(message = "Title can not be null.")
    private String title;

    @NotNull(message = "Description can not be null.")
    private String description;

    @NotNull(message = "Date can not be null.")
    private Date deadline;
}
