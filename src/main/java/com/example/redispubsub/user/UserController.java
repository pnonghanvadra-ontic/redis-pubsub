package com.example.redispubsub.user;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService =  userService;
    }

    @GetMapping("/{id}")
    public User get (@PathVariable("id") final String id){
        return userService.get(id);
    }

    @PostMapping("/add")
    public User add (@Valid @RequestBody User user){
        userService.put(user);
        userService.publish("User Invalidation");
        return user;
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable("id") final String id){
        userService.publish("User Invalidation");
        return (User) userService.delete(id);
    }

    @GetMapping("/publish/{msg}")
    public String publish(@PathVariable("msg") final String msg){
        userService.publish( msg);
        return msg;
    }
}
