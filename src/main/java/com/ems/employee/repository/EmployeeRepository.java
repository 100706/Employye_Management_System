package com.ems.employee.repository;

import com.ems.employee.entity.Employee;
import com.ems.employee.entity.EmployeeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE " +
           "(:department IS NULL OR LOWER(e.department) = LOWER(:department)) AND " +
           "(:designation IS NULL OR LOWER(e.designation) = LOWER(:designation)) AND " +
           "(:status IS NULL OR e.status = :status) AND " +
           "(:search IS NULL OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "                  LOWER(e.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "                  LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Employee> findAllFiltered(
            @Param("department") String department,
            @Param("designation") String designation,
            @Param("status") EmployeeStatus status,
            @Param("search") String search,
            Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsByEmailAndEmployeeIdNot(String email, Long employeeId);

    Page<Employee> findByDepartmentIgnoreCase(String department, Pageable pageable);

    Page<Employee> findByDesignationIgnoreCase(String designation, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Employee> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}
