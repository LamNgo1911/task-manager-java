package com.liamngo.taskmanager.task_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Title can not be null.")
    private String title;

    @NotNull(message = "Description can not be null.")
    private String description;

    @NotNull(message = "Date can not be null.")
    private Date deadline;
}
