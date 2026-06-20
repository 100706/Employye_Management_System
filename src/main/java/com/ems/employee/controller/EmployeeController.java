package com.ems.employee.controller;

import com.ems.employee.dto.EmployeeCreateDTO;
import com.ems.employee.dto.EmployeeDTO;
import com.ems.employee.dto.EmployeeUpdateDTO;
import com.ems.employee.entity.EmployeeStatus;
import com.ems.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Management", description = "Endpoints for managing employee details, search, filtering, and pagination.")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @Operation(summary = "Create a new employee", description = "Creates a new employee record. Validates email, phone, and salary details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload (validation errors or duplicate email)")
    })
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeCreateDTO createDTO) {
        EmployeeDTO created = employeeService.createEmployee(createDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieves details of an employee using their unique primary key identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details found"),
            @ApiResponse(responseCode = "404", description = "Employee not found with specified ID")
    })
    public ResponseEntity<EmployeeDTO> getEmployeeById(
            @Parameter(description = "ID of the employee to retrieve", required = true) @PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    @Operation(summary = "Get all employees (Filtered & Paginated)", description = "Retrieves list of employees with options to filter, search, paginate, and sort.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) EmployeeStatus status,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<EmployeeDTO> employees = employeeService.getAllEmployees(department, designation, status, search, page, size, sortBy, sortDir);
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee details", description = "Updates an existing employee record with new details. Validates all inputs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload or email collision"),
            @ApiResponse(responseCode = "404", description = "Employee not found with specified ID")
    })
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @Parameter(description = "ID of the employee to update", required = true) @PathVariable Long id,
            @Valid @RequestBody EmployeeUpdateDTO updateDTO) {
        EmployeeDTO updated = employeeService.updateEmployee(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee", description = "Permanently deletes an employee record from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found with specified ID")
    })
    public ResponseEntity<Void> deleteEmployee(
            @Parameter(description = "ID of the employee to delete", required = true) @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/department")
    @Operation(summary = "Search employees by department", description = "Retrieves employees matching a specific department name (case insensitive).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search results retrieved successfully")
    })
    public ResponseEntity<Page<EmployeeDTO>> searchByDepartment(
            @RequestParam String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<EmployeeDTO> employees = employeeService.searchByDepartment(department, page, size, sortBy, sortDir);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/designation")
    @Operation(summary = "Search employees by designation", description = "Retrieves employees matching a specific designation name (case insensitive).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search results retrieved successfully")
    })
    public ResponseEntity<Page<EmployeeDTO>> searchByDesignation(
            @RequestParam String designation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<EmployeeDTO> employees = employeeService.searchByDesignation(designation, page, size, sortBy, sortDir);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/name")
    @Operation(summary = "Search employees by name", description = "Retrieves employees where first or last name contains the query string (case insensitive).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search results retrieved successfully")
    })
    public ResponseEntity<Page<EmployeeDTO>> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<EmployeeDTO> employees = employeeService.searchByName(name, page, size, sortBy, sortDir);
        return ResponseEntity.ok(employees);
    }
}
