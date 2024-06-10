//package net.javaguides.springboot.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import net.javaguides.springboot.model.Employee;
//import net.javaguides.springboot.repository.EmployeeRepository;
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//	@Autowired
//	private EmployeeRepository employeeRepository;
//
//	@Override
//	public List<Employee> getAllEmployees() {
//		return employeeRepository.findAll();
//	}
//
//	@Override
//	public void saveEmployee(Employee employee) {
//		this.employeeRepository.save(employee);
//	}
//
//	@Override
//	public Employee getEmployeeById(long id) {
//		Optional<Employee> optional = employeeRepository.findById(id);
//		Employee employee = null;
//		if (optional.isPresent()) {
//			employee = optional.get();
//		} else {
//			throw new RuntimeException(" Employee not found for id :: " + id);
//		}
//		return employee;
//	}
//
//	@Override
//	public void deleteEmployeeById(long id) {
//		this.employeeRepository.deleteById(id);
//	}
//
//	@Override
//	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//			Sort.by(sortField).descending();
//		
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//		return this.employeeRepository.findAll(pageable);
//	}
//}
package net.project.springboot.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.project.springboot.model.Employee;
import net.project.springboot.model.User;
import net.project.springboot.repository.EmployeeRepository;
import net.project.springboot.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        employee.setUser(user);
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getEmployeesByUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        return employeeRepository.findByUser(user);
    }

    @Override
    public Page<Employee> findPaginatedByUser(int pageNo, int pageSize, String sortField, String sortDirection, String email) {
        User user = userRepository.findByEmail(email);
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        List<Employee> employees = employeeRepository.findByUser(user);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), employees.size());
        return new PageImpl<>(employees.subList(start, end), pageable, employees.size());
        
        
        
    }
}
