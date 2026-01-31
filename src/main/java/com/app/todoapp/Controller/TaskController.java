package com.app.todoapp.controller;

import com.app.todoapp.model.Task;
import com.app.todoapp.service.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ✅ Get todos for logged-in user
    @GetMapping
    public List<Task> getTodos() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return taskService.getTasksForUser(email);
    }

    // ✅ Add todo for logged-in user
    @PostMapping
    public void addTodo(@RequestBody Task task) {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        taskService.createTask(task.getTitle(), email);
    }

    @GetMapping("/{id}/toggle")
    public void toggle(@PathVariable Long id) {
        taskService.toggleTask(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
