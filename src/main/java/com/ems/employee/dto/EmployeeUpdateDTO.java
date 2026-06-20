package com.ems.employee.dto;

import com.ems.employee.entity.EmployeeStatus;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeUpdateDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number must be valid (10 to 15 digits, optionally starting with +)")
    private String phoneNumber;

    @NotBlank(message = "Department is required")
    @Size(max = 50, message = "Department must not exceed 50 characters")
    private String department;

    @NotBlank(message = "Designation is required")
    @Size(max = 50, message = "Designation must not exceed 50 characters")
    private String designation;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be a positive value")
    private BigDecimal salary;

    @NotNull(message = "Joining date is required")
    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate joiningDate;

    @NotNull(message = "Status is required")
    private EmployeeStatus status;

    public EmployeeUpdateDTO() {
    }

    public EmployeeUpdateDTO(String firstName, String lastName, String email, String phoneNumber,
                             String department, String designation, BigDecimal salary, LocalDate joiningDate, EmployeeStatus status) {
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

    public static EmployeeUpdateDTOBuilder builder() {
        return new EmployeeUpdateDTOBuilder();
    }

    public static class EmployeeUpdateDTOBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String department;
        private String designation;
        private BigDecimal salary;
        private LocalDate joiningDate;
        private EmployeeStatus status;

        public EmployeeUpdateDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeUpdateDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeUpdateDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeeUpdateDTOBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public EmployeeUpdateDTOBuilder department(String department) {
            this.department = department;
            return this;
        }

        public EmployeeUpdateDTOBuilder designation(String designation) {
            this.designation = designation;
            return this;
        }

        public EmployeeUpdateDTOBuilder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeUpdateDTOBuilder joiningDate(LocalDate joiningDate) {
            this.joiningDate = joiningDate;
            return this;
        }

        public EmployeeUpdateDTOBuilder status(EmployeeStatus status) {
            this.status = status;
            return this;
        }

        public EmployeeUpdateDTO build() {
            return new EmployeeUpdateDTO(firstName, lastName, email, phoneNumber, department, designation, salary, joiningDate, status);
        }
    }

    @Override
    public String toString() {
        return "EmployeeUpdateDTO{" +
                "firstName='" + firstName + '\'' +
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
