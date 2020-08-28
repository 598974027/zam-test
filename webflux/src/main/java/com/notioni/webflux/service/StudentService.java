package com.notioni.webflux.service;

import com.notioni.webflux.entity.Student;
import com.notioni.webflux.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    public Mono<Student> findStudentByCode(String code) {
        return studentRepository.findByCodeAndActiveTrue(code)
                //为空抛Mono.error
                .switchIfEmpty(Mono.error(new IllegalArgumentException("invalid student code: " + code)));
    }

    public Mono<Student> updateStudentProfile(Student student, String address, String remark) {
        if (!StringUtils.isEmpty(address) || !StringUtils.isEmpty(remark)) {
            if (!StringUtils.isEmpty(address)) {
                student.setAddress(address);
            }
            if (!StringUtils.isEmpty(remark)) {
                student.setRemark(remark);
            }
            return studentRepository.save(student);
        } else {
            //参数异常抛Mono.error
            return Mono.error(new IllegalArgumentException("invalid parameters student update profile"));
        }
    }

}
