package com.app.todoapp.controller;

import com.app.todoapp.model.Task;
import com.app.todoapp.service.TaskService;
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

    @GetMapping
    public List<Task> getTodos(@RequestParam String email) {
        return taskService.getTasksForUser(email);
    }

    @PostMapping
    public void addTodo(@RequestParam String email,
                        @RequestBody Task task) {
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
