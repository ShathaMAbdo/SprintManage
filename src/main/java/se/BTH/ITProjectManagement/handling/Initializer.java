package se.BTH.ITProjectManagement.handling;
import static org.apache.log4j.BasicConfigurator.configure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.BTH.ITProjectManagement.models.*;
import se.BTH.ITProjectManagement.repositories.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Initializer implements CommandLineRunner {

    private final UserRepository userrepo;
    private final RoleRepository rolerepo;
    private final TeamRepository teamrepo;
    private final TaskRepository taskRepo;
    private final SubTaskRepository subTaskRepo;
    private final SprintRepository sprintRepo;
    private final BacklogTaskRepository backlogTaskRepo;


    public Initializer(UserRepository repository, RoleRepository rolerepo, TeamRepository teamrepo, TaskRepository taskRepo, SubTaskRepository subTaskRepo, SprintRepository sprintRepo, BacklogTaskRepository backlogTaskRepo) {
        this.userrepo = repository;
        this.rolerepo = rolerepo;
        this.teamrepo = teamrepo;
        this.taskRepo = taskRepo;
        this.subTaskRepo = subTaskRepo;
        this.sprintRepo = sprintRepo;
        this.backlogTaskRepo = backlogTaskRepo;
    }

    void configure() {

    }

    @Override
    public void run(String... strings) {
        teamrepo.deleteAll();
        rolerepo.deleteAll();
        userrepo.deleteAll();
        taskRepo.deleteAll();
        subTaskRepo.deleteAll();
        sprintRepo.deleteAll();
        backlogTaskRepo.deleteAll();
        addPersons();
        addBacklog();
    }

    private void addBacklog() {
        Set<Task> tasks = new HashSet<>();
        //add first task
        List<User> users = new ArrayList<>();
        List<Integer> actualHours = new ArrayList<>();
        Set<SubTask> subTasks = new HashSet<>();
        actualHours.add(0, 1);
        actualHours.add(1, 0);
        actualHours.add(2, 4);
        actualHours.add(3, 1);
        User user = userrepo.findByUserName("Arti").get();
        users.add(user);
        SubTask subtask = SubTask.builder().name("Schema Registration").status(TaskStatus.DONE).users(users)
                .OEstimate(8).actualHours(actualHours).build();
        subTasks.add(subtask);

        users = new ArrayList<>();
        actualHours.add(0, 8);
        actualHours.add(1, 4);
        actualHours.add(2, 6);
        actualHours.add(3, 6);
        user = userrepo.findByUserName("Hossein").get();
        users.add(user);
        user = userrepo.findByUserName("Kevin").get();
        users.add(user);
        subtask = SubTask.builder().name("implement notification contxt and data").status(TaskStatus.ONGOING).users(users)
                .OEstimate(40).actualHours(actualHours).build();
        subTasks.add(subtask);

        users = new ArrayList<>();
        actualHours.set(0, 8);
        actualHours.set(1, 4);
        actualHours.set(2, 6);
        actualHours.set(1, 6);
        user = userrepo.findByUserName("Hossein").get();
        users.add(user);
        subtask = SubTask.builder().name("New notification category").status(TaskStatus.REVIEW).users(users)
                .OEstimate(2).actualHours(actualHours).build();
        subTasks.add(subtask);
        subTaskRepo.saveAll(subTasks);
        Task task = Task.builder().name("Implement of new OTC notification").priority(1).storyPoints(50).subTasks(subTasks)
                .build();
        tasks.add(task);
        // add  task 2
        users = new ArrayList<>();
        actualHours = new ArrayList<>();
        subTasks = new HashSet<>();
        actualHours.add(0, 1);
        actualHours.add(1, 1);
        actualHours.add(2, 0);
        actualHours.add(3, 2);
        user = userrepo.findByUserName("Emil").get();
        users.add(user);
        subtask = SubTask.builder().name("Test Analysis").status(TaskStatus.ONGOING).users(users)
                .OEstimate(24).actualHours(actualHours).build();
        subTasks.add(subtask);

        users = new ArrayList<>();
        actualHours.add(0, 7);
        actualHours.add(1, 7);
        actualHours.add(2, 8);
        actualHours.add(3, 1);
        user = userrepo.findByUserName("Emil").get();
        users.add(user);

        subtask = SubTask.builder().name("test data( RMCA & CPM)").status(TaskStatus.ONGOING).users(users)
                .OEstimate(40).actualHours(actualHours).build();
        subTasks.add(subtask);
        users = new ArrayList<>();
        actualHours.set(0, 0);
        actualHours.set(1, 0);
        actualHours.set(2, 0);
        actualHours.set(3, 2);
        user = userrepo.findByUserName("Emil").get();
        users.add(user);

        subtask = SubTask.builder().name("integration Test").status(TaskStatus.ONGOING).users(users)
                .OEstimate(40).actualHours(actualHours).build();
        subTasks.add(subtask);
        users = new ArrayList<>();
        actualHours.set(0, 4);
        actualHours.set(1, 0);
        actualHours.set(2, 0);
        actualHours.set(3, 0);
        user = userrepo.findByUserName("Forhan").get();
        users.add(user);
        subtask = SubTask.builder().name("Jive Test").status(TaskStatus.PLANNED).users(users)
                .OEstimate(64).actualHours(actualHours).build();
        subTasks.add(subtask);
        users = new ArrayList<>();
        actualHours.set(0, 0);
        actualHours.set(1, 0);
        actualHours.set(2, 0);
        actualHours.set(3, 1);
        user = userrepo.findByUserName("Forhan").get();
        users.add(user);
        subtask = SubTask.builder().name("Jive Protocol").status(TaskStatus.ONGOING).users(users)
                .OEstimate(8).actualHours(actualHours).build();
        subTasks.add(subtask);
        users = new ArrayList<>();
        actualHours.set(0, 2);
        actualHours.set(1, 0);
        actualHours.set(2, 12);
        actualHours.set(3, 11);
        user = userrepo.findByUserName("Simon").get();
        users.add(user);
        user = userrepo.findByUserName("Forhan").get();
        users.add(user);
        subtask = SubTask.builder().name("XML schemas").status(TaskStatus.ONGOING).users(users)
                .OEstimate(10).actualHours(actualHours).build();
        subTasks.add(subtask);
        subTaskRepo.saveAll(subTasks);
        task = Task.builder().name("Verification").priority(2).storyPoints(178).subTasks(subTasks)
                .build();
        tasks.add(task);
//add task 3
        users = new ArrayList<>();
        actualHours = new ArrayList<>();
        subTasks = new HashSet<>();
        actualHours.add(0, 0);
        actualHours.add(1, 0);
        actualHours.add(2, 0);
        actualHours.add(3, 0);
        user = userrepo.findByUserName("Arti").get();
        users.add(user);
        subtask = SubTask.builder().name("BSUCstudy doc update").status(TaskStatus.ONGOING).users(users)
                .OEstimate(16).actualHours(actualHours).build();
        subTasks.add(subtask);

        users = new ArrayList<>();
        actualHours.add(0, 0);
        actualHours.add(1, 0);
        actualHours.add(2, 0);
        actualHours.add(3, 0);
        user = userrepo.findByUserName("Forhan").get();
        users.add(user);

        subtask = SubTask.builder().name("Maven site update").status(TaskStatus.ONGOING).users(users)
                .OEstimate(16).actualHours(actualHours).build();
        subTasks.add(subtask);
        users = new ArrayList<>();
        actualHours.set(0, 0);
        actualHours.set(1, 0);
        actualHours.set(2, 0);
        actualHours.set(3, 2);
        user = userrepo.findByUserName("Emil").get();
        users.add(user);

        subtask = SubTask.builder().name("PI documentation update").status(TaskStatus.ONGOING).users(users)
                .OEstimate(16).actualHours(actualHours).build();

        subTasks.add(subtask);
        subTaskRepo.saveAll(subTasks);

        task = Task.builder().name("Documentation").priority(2).storyPoints(48).subTasks(subTasks)
                .build();
        tasks.add(task);
        //add task 4
        users = new ArrayList<>();
        actualHours = new ArrayList<>();
        subTasks = new HashSet<>();
        actualHours.add(0, 0);
        actualHours.add(1, 0);
        actualHours.add(2, 0);
        actualHours.add(3, 1);
        user = userrepo.findByUserName("Emil").get();
        users.add(user);
        subtask = SubTask.builder().name("New feature Control").status(TaskStatus.ONGOING).users(users)
                .OEstimate(80).actualHours(actualHours).build();
        subTasks.add(subtask);
        subTaskRepo.saveAll(subTasks);

        task = Task.builder().name("Introduction new feature Control").priority(4).storyPoints(8).subTasks(subTasks)
                .build();
        tasks.add(task);
//add task 5
        users = new ArrayList<>();
        actualHours = new ArrayList<>();
        subTasks = new HashSet<>();
        actualHours.add(0, 6);
        actualHours.add(1, 0);
        actualHours.add(2, 6);
        actualHours.add(3, 6);
        user = userrepo.findByUserName("Arti").get();
        users.add(user);
        user = userrepo.findByUserName("Hossein").get();
        users.add(user);
        user = userrepo.findByUserName("Kevin").get();
        users.add(user);
        user = userrepo.findByUserName("Emil").get();
        users.add(user);
        user = userrepo.findByUserName("Forhan").get();
        users.add(user);
        user = userrepo.findByUserName("Simon").get();
        users.add(user);
        subtask = SubTask.builder().name("Analyis").status(TaskStatus.ONGOING).users(users)
                .OEstimate(80).actualHours(actualHours).build();
        subTasks.add(subtask);
        subTaskRepo.saveAll(subTasks);
        task = Task.builder().name("Analysis").priority(5).storyPoints(80).subTasks(subTasks)
                .build();
        tasks.add(task);
        taskRepo.saveAll(tasks);
        Sprint sprint = Sprint.builder().name("CFT55 sprint 1910-1912").daily_meeting(LocalTime.of(13, 00))
                .delivery(LocalDate.of(2019, 3, 22)).demo(LocalDate.of(2019, 3, 21))
                .goal("Finish TR HX33029").retrospective(LocalDate.of(2019, 3, 21)).review(DayOfWeek.FRIDAY).build();
        sprintRepo.save(sprint);
        BacklogTask backlogTask = BacklogTask.builder().plannedPeriod(14).tasks(tasks).team(teamrepo.findByName("Team1").get())
                .sprint(sprint).build();
        backlogTaskRepo.save(backlogTask);
    }


    private void addPersons() {
        Role role = Role.builder().name(RoleName.ROLE_USER).build();
        rolerepo.save(role);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        role = Role.builder().name(RoleName.ROLE_ADMIN).build();
        rolerepo.save(role);
        List<User> users = new ArrayList<>();
        User u = User.builder().name("Arti").email("arti@bth.se").phone("0760762135").city("Karlskrona")
                .userName("Arti").password("1111").passwordConfirm("1111").roles(roles).build();
        users.add(u);
        u = User.builder().name("Hossein").email("hossein@bth.se").phone("0770772135").city("Karlshamen")
                .userName("Hossein").password("2222").passwordConfirm("2222").roles(roles).build();
        users.add(u);
        u = User.builder().name("Kevin").email("fridrek@bth.se").phone("0770772135").city("Karlshamen")
                .userName("Kevin").password("3333").passwordConfirm("3333").roles(roles).build();
        users.add(u);
        u = User.builder().name("Emil").email("emil@bth.se").phone("0770772135").city("Karlshamen")
                .userName("Emil").password("4444").passwordConfirm("4444").roles(roles).build();
        users.add(u);
        u = User.builder().name("Forhan").email("forhan@bth.se").phone("0770772135").city("Karlshamen")
                .userName("Forhan").password("5555").passwordConfirm("5555").roles(roles).build();
        users.add(u);
        u = User.builder().name("Simon").email("simon@bth.se").phone("0770772135").city("Karlshamen")
                .userName("Simon").password("6666").passwordConfirm("6666").roles(roles).build();
        users.add(u);
        userrepo.saveAll(users);

        Team team = Team.builder().name("Team1").users(users).build();
        teamrepo.save(team);
        users = new ArrayList<>();
        u = User.builder().name("Shatha").email("shatha@bth.se").phone("0760762135").city("Karlskrona")
                .userName("Shatha").password("7777").passwordConfirm("7777").roles(roles).build();
        users.add(u);
        u = User.builder().name("Hala").email("hala@bth.se").phone("0770772135").city("Karlshamen")
                .userName("Hala").password("8888").passwordConfirm("8888").roles(roles).build();
        users.add(u);
        u = User.builder().name("Robal").email("robal@bth.se").phone("0770772135").city("Karlshamen")
                .userName("Robal").password("9999").passwordConfirm("9999").roles(roles).build();
        users.add(u);

        userrepo.saveAll(users);
        team = Team.builder().name("Team2").users(users).build();
        teamrepo.save(team);

        roles.add(role);
        users = new ArrayList<>();
        u = User.builder().name("Admin").email("Admin@bth.se").phone("0760744135").city("Karlskrona")
                .userName("admin").password("admin").passwordConfirm("admin").roles(roles).build();
        users.add(u);
        userrepo.saveAll(users);
        team = Team.builder().name("Team Administrators").users(users).build();
        teamrepo.save(team);
teamrepo.findByName("Team1").get().getUsers().forEach(System.out::println);
        //  teamrepo.findAll().forEach(System.out::println);
    }

}