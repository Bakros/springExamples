package com.example.HTMLTableDemo.HTMLTable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //Constructor
    @JsonCreator
    public Student(@JsonProperty("id") Integer id,
                   @JsonProperty("name") String name,
                   @JsonProperty("age") Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
