package com.jgmedellin.springboot.thymeleafcruddemo.dao;

import com.jgmedellin.springboot.thymeleafcruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

  // add a method to sort by last name
  // Spring Data JPA will parse this method name and create the query for you
  public List<Employee> findAllByOrderByLastNameAsc();

}
