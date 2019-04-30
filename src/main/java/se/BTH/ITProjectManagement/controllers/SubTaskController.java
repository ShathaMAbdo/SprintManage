package se.BTH.ITProjectManagement.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.BTH.ITProjectManagement.models.Sprint;
import se.BTH.ITProjectManagement.models.SubTask;
import se.BTH.ITProjectManagement.models.Task;
import se.BTH.ITProjectManagement.models.TaskStatus;
import se.BTH.ITProjectManagement.repositories.SprintRepository;
import se.BTH.ITProjectManagement.repositories.SubTaskRepository;
import se.BTH.ITProjectManagement.repositories.TaskRepository;

import java.util.List;


@Controller
@RequestMapping("/api/subtask")
public class SubTaskController {

    private static org.apache.log4j.Logger log = Logger.getLogger(SubTaskController.class);

    @Autowired
    private SubTaskRepository repository;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private SprintRepository sprintRepo;


    // Displaying the initial subtasks list.
    @RequestMapping(value = "/subtasks", method = RequestMethod.GET)
    public String getSubTasks(Model model) {
        log.debug("Request to fetch all subtasks from the mongo database");
        List<SubTask> subtask_list = repository.findAll();
        model.addAttribute("subtasks", subtask_list);
        return "subtask";
    }

    // Opening the add new subtask form page.
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addSubTask(Model model) {
        log.debug("Request to open the new subtask form page");
        model.addAttribute("subtaskAttr", SubTask.builder().OEstimate(0).build());
        return "subtaskform";
    }

    // Opening the edit subtask form page.
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editSubTask(@RequestParam(value="id", required=true) String id,@RequestParam(value="taskid", required=true) String taskid,
                              @RequestParam(value="sprintid", required=true) String sprintid,Model model) {
        log.debug("Request to open the edit subtask form page");
        model.addAttribute("subtaskAttr", repository.findById(id).get());
        model.addAttribute("taskid", taskid);
        model.addAttribute("sprintid", sprintid);

        return "subtaskform";
    }

    // Deleting the specified subtask.
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) String id, Model model) {
        repository.deleteById(id);
        return "redirect:subtasks";
    }

    // Adding a new subtask or updating an existing subtask.
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("subtaskAttr") SubTask subtask) {
        if (subtask.getId()!="")
            repository.save(subtask);
        else {
            SubTask subtask1=SubTask.builder().name(subtask.getName()).actualHours(subtask.getActualHours()).OEstimate(subtask.getOEstimate())
                    .status(TaskStatus.PLANNED).users(subtask.getUsers()).build();
            repository.save(subtask1);
        }

        return "redirect:subtasks";
    }
}

/*
@RestController
@RequestMapping("/api")
public class SubTaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private SubTaskRepository repository;

    public SubTaskController(SubTaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/subtasks")
    Collection<SubTask> subtasks() {
        return repository.findAll();
    }

    @GetMapping("/subtask/{id}")
    ResponseEntity<?> getSubTask(@PathVariable String id) {
        Optional<SubTask> subtask = repository.findById(id);
        return subtask.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/subtask", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SubTask> createSubTask(@Valid @RequestBody SubTask subtask) throws URISyntaxException {
        log.info("Request to create subtask: {}", subtask);
        SubTask result = repository.save(subtask);
        return ResponseEntity.created(new URI("/api/subtask/" + result.getId())).body(result);
    }

    @PutMapping("/Subtask")
    ResponseEntity<SubTask> updateTask(@Valid @RequestBody SubTask subtask) {
        log.info("Request to update subtask: {}", subtask);
        SubTask result = repository.save(subtask);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/subtask/{id}")
    public ResponseEntity<?> deleteSubTask(@PathVariable String id) {
        log.info("Request to delete subtask: {}", id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
*/