package com.jgmedellin.springboot.thymeleafcruddemo.controller;

import com.jgmedellin.springboot.thymeleafcruddemo.entity.Employee;
import com.jgmedellin.springboot.thymeleafcruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService theEmployeeService) {
    employeeService = theEmployeeService;
  }

  @GetMapping("/list")
  public String listEmployees(Model theModel) {
    List<Employee> theEmployees = employeeService.findAll();  // get employees from db
    theModel.addAttribute("employees", theEmployees);  // add the data to the spring model
    return "employees/list-employees";
  }

  @GetMapping("/showAddForm")
  public String showAddForm(Model theModel) {
    Employee theEmployee = new Employee(); // create model attribute to bind form data
    theModel.addAttribute("employee", theEmployee); // add the data to the spring model
    return "employees/employee-form";
  }

  @GetMapping("/showUpdateForm")
  public String showUpdateForm(@RequestParam("employeeId") int theId, Model theModel) {
    Employee theEmployee = employeeService.findById(theId); // get the employee from the service
    theModel.addAttribute("employee", theEmployee); // set employee as a model attribute to pre-populate the form
    return "employees/employee-form"; // send over to our form
  }

  @GetMapping("/delete")
  public String delete(@RequestParam("employeeId") int theId) {
    employeeService.deleteById(theId); // delete the employee
    return "redirect:/employees/list"; // use a redirect to prevent duplicate submissions
  }

  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
    employeeService.save(theEmployee); // save the employee
    return "redirect:/employees/list"; // use a redirect to prevent duplicate submissions
  }

}