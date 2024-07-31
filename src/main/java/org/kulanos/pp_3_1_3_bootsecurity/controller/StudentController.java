package org.kulanos.pp_3_1_3_bootsecurity.controller;

import org.kulanos.pp_3_1_3_bootsecurity.model.Student;
import org.kulanos.pp_3_1_3_bootsecurity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "index";
    }

    @GetMapping("/student-create")
    public String createStudentForm(Student student) {
        return "create-student";
    }

    @PostMapping("/student-create")
    public String createStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/student-update/{id}")
    public String updateStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "create-student";
    }

    @GetMapping("/student-delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }
}
