package se.BTH.ITProjectManagement.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.BTH.ITProjectManagement.models.BacklogTask;
import se.BTH.ITProjectManagement.models.Task;
import se.BTH.ITProjectManagement.repositories.BacklogTaskRepository;
import se.BTH.ITProjectManagement.repositories.TaskRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BacklogTaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private BacklogTaskRepository repository;

    public BacklogTaskController(BacklogTaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/backlogtasks")
    Collection<BacklogTask> backlogTasks() {
        return repository.findAll();
    }

    @GetMapping("/backlogtask/{id}")
    ResponseEntity<?> getBacklogTask(@PathVariable String id) {
        Optional<BacklogTask> backlogtask = repository.findById(id);
        return backlogtask.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/backlogtask", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BacklogTask> createBacklogTask(@Valid @RequestBody BacklogTask backlogTask) throws URISyntaxException {
        log.info("Request to create backlogtask: {}", backlogTask);
        BacklogTask result = repository.save(backlogTask);
        return ResponseEntity.created(new URI("/api/backlogtask/" + result.getId())).body(result);
    }

    @PutMapping("/backlogtask")
    ResponseEntity<BacklogTask> updateTask(@Valid @RequestBody BacklogTask backlogTask) {
        log.info("Request to update backlogtask: {}", backlogTask);
        BacklogTask result = repository.save(backlogTask);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/backlogtask/{id}")
    public ResponseEntity<?> deleteBacklogTask(@PathVariable String id) {
        log.info("Request to delete backlogtask: {}", id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
