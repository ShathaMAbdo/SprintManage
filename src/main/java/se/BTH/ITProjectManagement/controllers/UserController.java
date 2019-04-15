package se.BTH.ITProjectManagement.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.BTH.ITProjectManagement.models.Role;
import se.BTH.ITProjectManagement.models.RoleName;
import se.BTH.ITProjectManagement.models.User;
import se.BTH.ITProjectManagement.repositories.TeamRepository;
import se.BTH.ITProjectManagement.repositories.UserRepository;
import se.BTH.ITProjectManagement.security.SecurityService;
import se.BTH.ITProjectManagement.security.UserService;
import se.BTH.ITProjectManagement.security.UserValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
//@RequestMapping("/api/user")
public class UserController {

    private static org.apache.log4j.Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserRepository repository;
//    @Autowired
//    private TeamRepository teamRepository;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;
    // Displaying the initial users list.

    @RequestMapping(value = "/api/user/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        log.debug("Request to fetch all users from the mongo database");
        List<User> user_list = repository.findAll();
        model.addAttribute("users", user_list);
        return "user";
    }

    // Opening the add new user form page.
    @RequestMapping(value = "/api/user/add", method = RequestMethod.GET)
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
    @RequestMapping(value = "/api/user/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam(value="id", required=true) String id, Model model) {
        log.debug("Request to open the edit user form page");
        model.addAttribute("userAttr", repository.findById(id));
        return "userform";
    }
    @RequestMapping(value = "/api/user/profile", method = RequestMethod.GET)
    public String profile(User user, Model model) {
        log.debug("Request to open the edit user form page");
        model.addAttribute("userAttr",user);
        return "profile";
    }
    // Deleting the specified user.
    @RequestMapping(value = "/api/user/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) String id, Model model) {
        repository.deleteById(id);
        return "redirect:users";
    }

    // Adding a new user or updating an existing user.
    @RequestMapping(value = "/api/user/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("userAttr") User user) {                  // needs test for edit or create
        repository.save(user);
        return "redirect:users";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        Set<Role> roles= new HashSet<>();
        roles.add(Role.builder().name(RoleName.ROLE_USER).build());

        model.addAttribute("userForm", User.builder().roles(roles).build());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "login";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "/login";
    }

//    @GetMapping({"/", "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }
}