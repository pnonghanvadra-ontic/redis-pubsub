package com.example.redispubsub.vm;

import com.example.redispubsub.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vm")
public class VMController {

    private VM vm;

    @Autowired
    public VMController(VM vm) {
        this.vm = vm;
    }

    @PostMapping("/add")
    public User add(@Valid @RequestBody User user){
        vm.add(user);
        return vm.find(user.getId());
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable("id") final String id){
        return vm.delete(id);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") final String id){
        return vm.find(id);
    }

    @GetMapping("/subscribe/{topic}")
    public String subscribe(@PathVariable("topic") final String topic){
        vm.subscribe(topic);
        return topic + " subscribed";
    }

    @GetMapping("/unsubscribe/{topic}")
    public String unsubscribe(@PathVariable("topic") final String topic){
        vm.unsubscribe(topic);
        return topic + " unsubscribed";
    }

}
