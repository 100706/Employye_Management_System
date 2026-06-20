package com.ems.employee.dto;

import com.ems.employee.entity.EmployeeStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String designation;
    private BigDecimal salary;
    private LocalDate joiningDate;
    private EmployeeStatus status;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long employeeId, String firstName, String lastName, String email, String phoneNumber,
                       String department, String designation, BigDecimal salary, LocalDate joiningDate, EmployeeStatus status) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public static EmployeeDTOBuilder builder() {
        return new EmployeeDTOBuilder();
    }

    public static class EmployeeDTOBuilder {
        private Long employeeId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String department;
        private String designation;
        private BigDecimal salary;
        private LocalDate joiningDate;
        private EmployeeStatus status;

        public EmployeeDTOBuilder employeeId(Long employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public EmployeeDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeeDTOBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public EmployeeDTOBuilder department(String department) {
            this.department = department;
            return this;
        }

        public EmployeeDTOBuilder designation(String designation) {
            this.designation = designation;
            return this;
        }

        public EmployeeDTOBuilder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeDTOBuilder joiningDate(LocalDate joiningDate) {
            this.joiningDate = joiningDate;
            return this;
        }

        public EmployeeDTOBuilder status(EmployeeStatus status) {
            this.status = status;
            return this;
        }

        public EmployeeDTO build() {
            return new EmployeeDTO(employeeId, firstName, lastName, email, phoneNumber, department, designation, salary, joiningDate, status);
        }
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                ", joiningDate=" + joiningDate +
                ", status=" + status +
                '}';
    }
}
