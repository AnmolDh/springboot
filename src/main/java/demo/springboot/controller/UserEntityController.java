package demo.springboot.controller;

import demo.springboot.entity.JournalEntity;
import demo.springboot.entity.UserEntity;
import demo.springboot.service.JournalService;
import demo.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntityController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public void createUser (@RequestBody UserEntity user) {
        userService.saveEntity(user);
    }

    @PutMapping("/{username}")
    public void updateUser (@RequestBody UserEntity user, @PathVariable String username) {
        UserEntity byUser = userService.findByUsername(username);
        if (byUser != null) {
            byUser.setUsername(user.getUsername());
            byUser.setPassword(user.getPassword());
            userService.saveEntity(byUser);
        }
    }
}
