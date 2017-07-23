/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigdata.repositories;

import com.bigdata.entities.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long>{
    @Override
    List<Student> findAll();
    void delete(Long id);
    Student findByEmail(String email);
    List<Student> findByEmailContaining(String email);
    
}
