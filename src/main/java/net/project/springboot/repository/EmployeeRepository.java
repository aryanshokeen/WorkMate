//package net.javaguides.springboot.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import net.javaguides.springboot.model.Employee;
//
//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long>{
//
//}




package net.project.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.project.springboot.model.Employee;
import net.project.springboot.model.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByUser(User user);
}
