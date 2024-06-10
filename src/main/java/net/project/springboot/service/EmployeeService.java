//package net.javaguides.springboot.service;
//
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//
//import net.javaguides.springboot.model.Employee;
//
//public interface EmployeeService {
//	List<Employee> getAllEmployees();
//	void saveEmployee(Employee employee);
//	Employee getEmployeeById(long id);
//	void deleteEmployeeById(long id);
//	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
//}

package net.project.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.project.springboot.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee, String userEmail);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    List<Employee> getEmployeesByUserEmail(String email);
    Page<Employee> findPaginatedByUser(int pageNo, int pageSize, String sortField, String sortDirection, String email);
}
