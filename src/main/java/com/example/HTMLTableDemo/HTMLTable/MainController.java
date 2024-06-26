package com.example.HTMLTableDemo.HTMLTable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }


    @GetMapping("/students")
    public String showStudent(Model model){

        List<Student> tempStudents = new ArrayList<Student>();

        tempStudents.add(new Student(1,"Juan",20));
        tempStudents.add(new Student(2,"Pedro",19));
        tempStudents.add(new Student(3,"Maria",25));

        System.out.println("hi");
        model.addAttribute("students", tempStudents);

        return "show-students";
    }


    /*
    Si el payload del post tiene solo el json este método funciona
    pero si el json va dentro de un parametro esto se cae por protocolo.
     */
//    @PostMapping("/displayStudent")
//    public String displayStudent(@RequestBody List<Student> studentList){
//        for(Student student : studentList){
//            System.out.println(student.getId() + ". Age: " + student.getAge() + " Name:" + student.getName());
//        }
//        return "displayStudent";
//    }

    @PostMapping("/displayStudent")
    public String displayStudent(@RequestParam("formData") String value, Model model) throws JsonProcessingException {

        System.out.println("The value is > "+ value);

        ObjectMapper mapper = new ObjectMapper();
        List<Student> students = mapper.readValue(value, new TypeReference<List<Student>>(){});;

        model.addAttribute("students",students);

        return "displayStudent";
    }


}
