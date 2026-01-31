package com.app.todoapp.controller;



import com.app.todoapp.model.Task;
import com.app.todoapp.service.TaskService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@CrossOrigin
public class TaskController {

    private final TaskService taskservice;

    public TaskController(TaskService taskservice) {
        this.taskservice = taskservice;
    }
    @GetMapping("/")
    public String getTask(Model model){
        List<Task> tasks = taskservice.getAllTasks();
        model.addAttribute("tasks",tasks);
        return "tasks";
    }
   
    @PostMapping
    public String createTask(@RequestParam String title){
       taskservice.createTask(title);
        return "redirect:/";
    }
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskservice.deleteTask(id);
        return "redirect:/";
    }
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        taskservice.toggleTask(id);
        return "redirect:/";
    }
}
