package se.BTH.ITProjectManagement.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.BTH.ITProjectManagement.models.Role;
import se.BTH.ITProjectManagement.models.RoleName;
import se.BTH.ITProjectManagement.models.Team;
import se.BTH.ITProjectManagement.models.User;
import se.BTH.ITProjectManagement.repositories.TeamRepository;
import se.BTH.ITProjectManagement.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/api/user")
public class UserController {

    private static org.apache.log4j.Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserRepository repository;
    @Autowired
    private TeamRepository teamRepository;
    // Displaying the initial users list.
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        log.debug("Request to fetch all users from the mongo database");
        List<User> user_list = repository.findAll();
        model.addAttribute("users", user_list);
        return "user";
    }

    // Opening the add new user form page.
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        log.debug("Request to open the new user form page");
       Set<Role> roles= new HashSet<>();
        roles.add(Role.builder().name(RoleName.ROLE_USER).build());
        User user=User.builder().roles(roles).build();
        repository.save(user);
        model.addAttribute("userAttr", user);
        return "userform";
    }

    // Opening the edit user form page.
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam(value="id", required=true) String id, Model model) {
        log.debug("Request to open the edit user form page");
        model.addAttribute("userAttr", repository.findById(id));
        return "userform";
    }

    // Deleting the specified user.
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) String id, Model model) {
        repository.deleteById(id);
        return "redirect:users";
    }

    // Adding a new user or updating an existing user.
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("userAttr") User user) {                  // needs test for edit or create
        repository.save(user);
        return "redirect:users";
    }
}