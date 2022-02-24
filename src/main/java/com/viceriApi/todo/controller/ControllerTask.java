package com.viceriApi.todo.controller;

import com.viceriApi.todo.entities.Task;
import com.viceriApi.todo.enums.PriorityTask;
import com.viceriApi.todo.enums.StatusTask;
import com.viceriApi.todo.repository.RepositoryTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.List;

@RestController
public class ControllerTask {


    @Autowired
    private RepositoryTask repositoryTask;


    @PostMapping("/saveTask")
    public ResponseEntity<Task> task (@RequestBody Task task, @RequestParam(required = false) PriorityTask priority, @RequestHeader HttpHeaders headers){
        try {
            repositoryTask.save(task);
            return ResponseEntity.ok(task);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/task")
    public ResponseEntity<List<Task>> taskAll() {
        List<Task> tasks = (List<Task>) repositoryTask.findAll();

        if(tasks.size()== 0) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/{cod}")
    public ResponseEntity<Task> taskId(@PathVariable Long id) {
        Task task = repositoryTask.findById(id).orElse(null);
        if (task == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> taskDelete(@PathVariable Long id) {
        try {
            Task task = repositoryTask.findById(id).orElse(null);
            if (task == null) {
                return ResponseEntity.status(404).build();
            } else {
                repositoryTask.delete(task);

                return ResponseEntity.ok(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping(value = "/task/{id}")
    public ResponseEntity<Task> taskStatus(@PathVariable Long id, @RequestBody Task task) {

        return repositoryTask.findById(id)
                .map(record -> {
                    Task update = repositoryTask.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/Task/filter")
    public ResponseEntity<List<Task>> taskFilterStatus(@RequestParam(name = "status", required = true) String status, @RequestParam(name = "priority", required = false) String priority) {
        Task task = new Task();

        if (priority != null) {

            List<Task> answer = task.ByPriority(priority);
            if (answer.size() == 0) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok(answer);
        }

         status = "in_progress";

        List<Task> answer = task.ByStatus(status);
        if (answer.size() == 0) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(answer);

    }

}
