package se.BTH.ITProjectManagement.services;


import se.BTH.ITProjectManagement.models.User;

import java.security.Principal;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    Boolean isAdmin(String userName);
}