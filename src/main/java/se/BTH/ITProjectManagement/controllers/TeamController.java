package se.BTH.ITProjectManagement.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.BTH.ITProjectManagement.models.Sprint;
import se.BTH.ITProjectManagement.models.Team;


import se.BTH.ITProjectManagement.models.User;
import se.BTH.ITProjectManagement.repositories.SprintRepository;
import se.BTH.ITProjectManagement.repositories.TeamRepository;
import se.BTH.ITProjectManagement.repositories.UserRepository;
import se.BTH.ITProjectManagement.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/team")
public class TeamController {

    private static org.apache.log4j.Logger log = Logger.getLogger(TeamController.class);

    @Autowired
    private TeamRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private UserService userService;

    // Displaying the initial teams list.
    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public String getTeams(Model model, Principal user) {
        log.debug("Request to fetch all teams from the mongo database");
        Boolean isAdmin=userService.isAdmin(user.getName());
        List<Team> team_list;
        if (isAdmin)
        team_list = repository.findAll();
        else
         team_list = repository.findAll().stream().filter(t->t.isUserInTeam(user)).collect(Collectors.toList());
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("teams", team_list);
        return "team";
    }

    // Opening the add new team form page.
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTeam(Model model) {
        log.debug("Request to open the new team form page");
        Team team = Team.builder().active(true).build();
        model.addAttribute("teamAttr", team);
        return "teamform";
    }

    // Opening the edit team form page.
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editTeam(@RequestParam(value = "id", required = true) String id, Model model) {
        log.debug("Request to open the edit team form page");
        Team team = repository.findById(id).get();
        model.addAttribute("teamAttr", team);
        return "teamform";
    }

    // Opening the edit team form page.
    @RequestMapping(value = "/sprintteam", method = RequestMethod.GET)
    public String sprintteam(@RequestParam(value = "sprintid", required = true) String id, Model model,Principal user) {
        log.debug("Request to open the edit team form page");
        Team team;
        Sprint sprint = sprintRepository.findById(id).get();
        if (sprint.getTeam() != null) {
            team = sprint.getTeam();
            List<User> member_list = team.getUsers();
            member_list.removeIf(u -> u.isActive() == false);
            team.setUsers(member_list);
        } else team = Team.builder().active(true).users(new ArrayList<>()).build();
        Boolean isAdmin=userService.isAdmin(user.getName());
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("teamAttr", team);
        model.addAttribute("sprintid", id);
        return "sprintteamform";
    }

    // view all  users.
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String members(@RequestParam("id") String id, Model model) {
        log.debug("Request to fetch all users from the mongo database");
        Team team = repository.findById(id).get();
        List<User> user_list = userRepository.findAll();
        user_list.removeIf(u -> !u.isActive());
        model.addAttribute("members", user_list);
        model.addAttribute("team", team);
        return "teammember";
    }

    // add member to team and redirect to team page.
    @RequestMapping(value = "/addmember", method = RequestMethod.GET)
    public String addmember(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "teamid", required = true) String teamid, Model model) {
        Team team = repository.findById(teamid).get();
        List<User> members = team.getUsers();
        User user = userRepository.findById(id).get();
        if (!team.isMememberExcit(user)) {
            team.getUsers().add(user);
            team.setUsers(members);
        }
        repository.save(team);
        return "redirect:/api/team/edit?id=" + team.getId();
    }

    // Deleting the specified team.
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) String id, Model model) {
        repository.deleteById(id);
        return "redirect:teams";
    }

    // Deleting the specified team.
    @RequestMapping(value = "/enable", method = RequestMethod.GET)
    public String enable(@RequestParam(value = "id", required = true) String id, Model model) {
        Team team = repository.findById(id).get();
        team.changeActive();
        repository.save(team);
        return "redirect:teams";
    }

    // Adding a new team or updating an existing team.
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("teamAttr") Team team) {
        List<User> users = new ArrayList<>();
        if (team.getId().equals("")) {
            Team team1 = Team.builder().name(team.getName()).active(true).users(users).build();
            repository.save(team1);
        } else {
            List<User> members = team.getUsers();
            members.removeIf(u -> u.isActive() == false);
            team.setUsers(members);
            repository.save(team);
        }
        return "redirect:teams";
    }
}
