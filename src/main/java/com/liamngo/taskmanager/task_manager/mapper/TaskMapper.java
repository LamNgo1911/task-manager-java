package com.liamngo.taskmanager.task_manager.mapper;

import com.liamngo.taskmanager.task_manager.dto.TaskCreateDto;
import com.liamngo.taskmanager.task_manager.dto.TaskReadDto;
import com.liamngo.taskmanager.task_manager.dto.TaskUpdateDto;
import com.liamngo.taskmanager.task_manager.model.Task;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskReadDto toTaskReadDto(Task task);

    @Mapping(target = "id", ignore = true)
    Task toTask(TaskCreateDto taskCreateDto);

    List<TaskReadDto> toListTaskReadDto(List<Task> tasks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskFromDto(TaskUpdateDto taskUpdateDto, @MappingTarget Task task);
}
