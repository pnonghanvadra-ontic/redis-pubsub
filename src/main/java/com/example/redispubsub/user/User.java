package com.example.redispubsub.user;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private Integer salary;

    public User(){}

    public User(String email, String name, Integer salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getSalary(){
        return salary;
    }
    @Override
    public String toString() {
        return "User: "+"{\n\tID: "+this.id+"\n\t"+"Name: "+(this.name==null?"-":this.name)+"\n\t"+"Salary: "+(this.salary==null? "0" : this.salary)+"\n}";
    }
}


