package com.app.todoapp.service;


import com.app.todoapp.model.Task;
import com.app.todoapp.repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
    return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task=new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
        return;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
        return;
    }

    public void toggleTask(Long id) {
        Task task=taskRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
        return;
    }
}
