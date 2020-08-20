package com.notioni.webflux.controller;

import com.notioni.webflux.ResultBase;
import com.notioni.webflux.entity.Student;
import com.notioni.webflux.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/findAll")
    public Flux<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/findOne/{code}")
    public Mono<Student> findOne(@PathVariable("code") String code) {
        return studentService.findStudentByCode(code);
    }

    @PostMapping("/{code}")
    public Mono<ResultBase> modify(@PathVariable("code") String code) {
        return studentService.findStudentByCode(code)
                .zipWhen(student -> studentService.updateStudentProfile(student, "武汉2", "love"))
                .map(student -> ResultBase.OK());
    }

}
