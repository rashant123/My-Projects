
package com.bigdata.controllers;

import com.bigdata.entities.Student;
import com.bigdata.repositories.StudentRepository;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/hello")
    public String  hello(){
     return "Hello";   
    }
    
    @GetMapping("/students")
    public List<Student>  findStudents(){
     return  studentRepository.findAll();
    
    }
    
    @DeleteMapping("/students/delete/{id}")
    public void delete(@PathVariable Long id){
        studentRepository.delete(id);
    }
    
    @PostMapping("/students")
    public ResponseEntity<?> saveStudent(@RequestBody Student student){
        try {
            studentRepository.save(student);
            return ResponseEntity.ok("Data inserted to the database");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Couldnot insert the data");
        }
        
    }
    
    
    @GetMapping("/students/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable Long id){
        Student stu =  studentRepository.findOne(id);
        if(stu==null){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok("Required student with ID "+ id + " is "+stu.getName() );
        }
    }
    @GetMapping(value = "/students",params = "email")
    public ResponseEntity<?> findStudentByEmail(@RequestParam String email){
        Student stu = studentRepository.findByEmail(email);
        if(stu==null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(stu);
        }
    }
    
    
    @GetMapping(value = "/students", params = {"email","action=search"})
    public ResponseEntity<?> findStudentByEmailContaining(String email){
        List <Student> stuList = studentRepository.findByEmailContaining(email);
        if(stuList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(stuList);
        }
        
    }
    
     @RequestMapping("/user")
     public Principal user(Principal principal) {
     return principal;
  }

    
    
//    @PutMapping("/students/{id}")
//    public ResponseEntity<?> updateStudent(@PathVariable Long id,@RequestBody Student student,@RequestParam String action){
//        Student stu = studentRepository.findById(Long id);
//    }
}
