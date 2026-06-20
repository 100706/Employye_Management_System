package com.ems.employee.service;

import com.ems.employee.dto.EmployeeCreateDTO;
import com.ems.employee.dto.EmployeeDTO;
import com.ems.employee.dto.EmployeeUpdateDTO;
import com.ems.employee.entity.Employee;
import com.ems.employee.entity.EmployeeStatus;
import com.ems.employee.exception.EmployeeNotFoundException;
import com.ems.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeCreateDTO createDTO;
    private EmployeeUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .employeeId(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phoneNumber("+1234567890")
                .department("Engineering")
                .designation("Software Engineer")
                .salary(new BigDecimal("85000.00"))
                .joiningDate(LocalDate.of(2023, 1, 15))
                .status(EmployeeStatus.ACTIVE)
                .build();

        createDTO = EmployeeCreateDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phoneNumber("+1234567890")
                .department("Engineering")
                .designation("Software Engineer")
                .salary(new BigDecimal("85000.00"))
                .joiningDate(LocalDate.of(2023, 1, 15))
                .status(EmployeeStatus.ACTIVE)
                .build();

        updateDTO = EmployeeUpdateDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phoneNumber("+1234567890")
                .department("Engineering")
                .designation("Senior Software Engineer")
                .salary(new BigDecimal("95000.00"))
                .joiningDate(LocalDate.of(2023, 1, 15))
                .status(EmployeeStatus.ACTIVE)
                .build();
    }

    @Test
    void createEmployee_Success() {
        when(employeeRepository.existsByEmail(createDTO.getEmail())).thenReturn(false);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO result = employeeService.createEmployee(createDTO);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(createDTO.getEmail());
        assertThat(result.getEmployeeId()).isEqualTo(1L);
        verify(employeeRepository, times(1)).existsByEmail(createDTO.getEmail());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void createEmployee_ThrowsException_WhenEmailExists() {
        when(employeeRepository.existsByEmail(createDTO.getEmail())).thenReturn(true);

        assertThatThrownBy(() -> employeeService.createEmployee(createDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email already exists");

        verify(employeeRepository, times(1)).existsByEmail(createDTO.getEmail());
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void getEmployeeById_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeDTO result = employeeService.getEmployeeById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("John");
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_ThrowsException_WhenNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(1L))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("Employee not found");

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getAllEmployees_Success() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("employeeId").ascending());
        Page<Employee> page = new PageImpl<>(Collections.singletonList(employee));
        when(employeeRepository.findAllFiltered(null, null, null, null, pageable)).thenReturn(page);

        Page<EmployeeDTO> result = employeeService.getAllEmployees(null, null, null, null, 0, 10, "employeeId", "asc");

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getEmployeeId()).isEqualTo(1L);
        verify(employeeRepository, times(1)).findAllFiltered(null, null, null, null, pageable);
    }

    @Test
    void updateEmployee_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.existsByEmailAndEmployeeIdNot(updateDTO.getEmail(), 1L)).thenReturn(false);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO result = employeeService.updateEmployee(1L, updateDTO);

        assertThat(result).isNotNull();
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).existsByEmailAndEmployeeIdNot(updateDTO.getEmail(), 1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateEmployee_ThrowsException_WhenNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(1L, updateDTO))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("Employee not found");

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void updateEmployee_ThrowsException_WhenEmailInUseByOther() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.existsByEmailAndEmployeeIdNot(updateDTO.getEmail(), 1L)).thenReturn(true);

        assertThatThrownBy(() -> employeeService.updateEmployee(1L, updateDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email already in use");

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).existsByEmailAndEmployeeIdNot(updateDTO.getEmail(), 1L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    void deleteEmployee_ThrowsException_WhenNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.deleteEmployee(1L))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("Employee not found");

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).delete(any(Employee.class));
    }

    @Test
    void searchByDepartment_Success() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("employeeId").ascending());
        Page<Employee> page = new PageImpl<>(Collections.singletonList(employee));
        when(employeeRepository.findByDepartmentIgnoreCase("Engineering", pageable)).thenReturn(page);

        Page<EmployeeDTO> result = employeeService.searchByDepartment("Engineering", 0, 10, "employeeId", "asc");

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        verify(employeeRepository, times(1)).findByDepartmentIgnoreCase("Engineering", pageable);
    }

    @Test
    void searchByDesignation_Success() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("employeeId").ascending());
        Page<Employee> page = new PageImpl<>(Collections.singletonList(employee));
        when(employeeRepository.findByDesignationIgnoreCase("Software Engineer", pageable)).thenReturn(page);

        Page<EmployeeDTO> result = employeeService.searchByDesignation("Software Engineer", 0, 10, "employeeId", "asc");

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        verify(employeeRepository, times(1)).findByDesignationIgnoreCase("Software Engineer", pageable);
    }

    @Test
    void searchByName_Success() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("employeeId").ascending());
        Page<Employee> page = new PageImpl<>(Collections.singletonList(employee));
        when(employeeRepository.findByNameContainingIgnoreCase("John", pageable)).thenReturn(page);

        Page<EmployeeDTO> result = employeeService.searchByName("John", 0, 10, "employeeId", "asc");

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        verify(employeeRepository, times(1)).findByNameContainingIgnoreCase("John", pageable);
    }
}
