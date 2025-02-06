package oauth.resource.server.oauth_resource_server.controller;


import oauth.resource.server.oauth_resource_server.dto.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @GetMapping()
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("John", "Doe"));
        users.add(new User("Jane", "Doe"));
        users.add(new User("Jack", "Doe"));
        users.add(new User("Jill", "Doe"));
        users.add(new User("Jack", "Doe"));
        users.add(new User("Jill", "Doe"));
        return users;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createUser(@RequestBody User user) {
        return "user created";
    }


}
