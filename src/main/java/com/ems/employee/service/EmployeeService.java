package com.ems.employee.service;

import com.ems.employee.dto.EmployeeCreateDTO;
import com.ems.employee.dto.EmployeeDTO;
import com.ems.employee.dto.EmployeeUpdateDTO;
import com.ems.employee.entity.EmployeeStatus;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeCreateDTO employeeCreateDTO);

    EmployeeDTO getEmployeeById(Long employeeId);

    Page<EmployeeDTO> getAllEmployees(
            String department,
            String designation,
            EmployeeStatus status,
            String search,
            int page,
            int size,
            String sortBy,
            String sortDir);

    EmployeeDTO updateEmployee(Long employeeId, EmployeeUpdateDTO employeeUpdateDTO);

    void deleteEmployee(Long employeeId);

    Page<EmployeeDTO> searchByDepartment(String department, int page, int size, String sortBy, String sortDir);

    Page<EmployeeDTO> searchByDesignation(String designation, int page, int size, String sortBy, String sortDir);

    Page<EmployeeDTO> searchByName(String name, int page, int size, String sortBy, String sortDir);
}
