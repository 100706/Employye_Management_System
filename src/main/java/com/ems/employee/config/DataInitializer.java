package com.ems.employee.config;

import com.ems.employee.entity.Employee;
import com.ems.employee.entity.EmployeeStatus;
import com.ems.employee.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("h2")
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(Arrays.asList(
                    Employee.builder()
                        .firstName("John").lastName("Doe")
                        .email("john.doe@example.com").phoneNumber("+1234567890")
                        .department("Engineering").designation("Software Engineer")
                        .salary(new BigDecimal("85000.00")).joiningDate(LocalDate.of(2023, 1, 15))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Jane").lastName("Smith")
                        .email("jane.smith@example.com").phoneNumber("+1987654321")
                        .department("Human Resources").designation("HR Manager")
                        .salary(new BigDecimal("75000.00")).joiningDate(LocalDate.of(2022, 6, 10))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Bob").lastName("Johnson")
                        .email("bob.johnson@example.com").phoneNumber("+1122334455")
                        .department("Engineering").designation("Tech Lead")
                        .salary(new BigDecimal("120000.00")).joiningDate(LocalDate.of(2020, 3, 22))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Alice").lastName("Williams")
                        .email("alice.williams@example.com").phoneNumber("+1555444333")
                        .department("Marketing").designation("Marketing Specialist")
                        .salary(new BigDecimal("65000.00")).joiningDate(LocalDate.of(2024, 2, 1))
                        .status(EmployeeStatus.INACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Michael").lastName("Brown")
                        .email("michael.brown@example.com").phoneNumber("+1333444555")
                        .department("Sales").designation("Sales Executive")
                        .salary(new BigDecimal("55000.00")).joiningDate(LocalDate.of(2023, 5, 12))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Emily").lastName("Davis")
                        .email("emily.davis@example.com").phoneNumber("+1444555666")
                        .department("Finance").designation("Financial Analyst")
                        .salary(new BigDecimal("72000.00")).joiningDate(LocalDate.of(2022, 11, 30))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("William").lastName("Miller")
                        .email("william.miller@example.com").phoneNumber("+1666777888")
                        .department("Engineering").designation("QA Engineer")
                        .salary(new BigDecimal("68000.00")).joiningDate(LocalDate.of(2023, 8, 19))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Olivia").lastName("Garcia")
                        .email("olivia.garcia@example.com").phoneNumber("+1777888999")
                        .department("Human Resources").designation("Recruiter")
                        .salary(new BigDecimal("60000.00")).joiningDate(LocalDate.of(2024, 1, 10))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("James").lastName("Rodriguez")
                        .email("james.rodriguez@example.com").phoneNumber("+1888999000")
                        .department("Sales").designation("Sales Manager")
                        .salary(new BigDecimal("95000.00")).joiningDate(LocalDate.of(2021, 4, 15))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Sophia").lastName("Martinez")
                        .email("sophia.martinez@example.com").phoneNumber("+1999000111")
                        .department("Marketing").designation("Marketing Manager")
                        .salary(new BigDecimal("90000.00")).joiningDate(LocalDate.of(2021, 9, 1))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Benjamin").lastName("Hernandez")
                        .email("benjamin.hernandez@example.com").phoneNumber("+1222333444")
                        .department("Finance").designation("Finance Manager")
                        .salary(new BigDecimal("105000.00")).joiningDate(LocalDate.of(2020, 10, 5))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Isabella").lastName("Lopez")
                        .email("isabella.lopez@example.com").phoneNumber("+1555666777")
                        .department("Engineering").designation("DevOps Engineer")
                        .salary(new BigDecimal("92000.00")).joiningDate(LocalDate.of(2022, 2, 28))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Lucas").lastName("Gonzalez")
                        .email("lucas.gonzalez@example.com").phoneNumber("+1444333222")
                        .department("Engineering").designation("Software Engineer")
                        .salary(new BigDecimal("82000.00")).joiningDate(LocalDate.of(2023, 10, 1))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Mia").lastName("Wilson")
                        .email("mia.wilson@example.com").phoneNumber("+1222111000")
                        .department("Human Resources").designation("HR Coordinator")
                        .salary(new BigDecimal("50000.00")).joiningDate(LocalDate.of(2023, 7, 15))
                        .status(EmployeeStatus.INACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Alexander").lastName("Anderson")
                        .email("alexander.anderson@example.com").phoneNumber("+1999888777")
                        .department("Sales").designation("Account Executive")
                        .salary(new BigDecimal("70000.00")).joiningDate(LocalDate.of(2022, 5, 20))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Charlotte").lastName("Thomas")
                        .email("charlotte.thomas@example.com").phoneNumber("+1888777666")
                        .department("Finance").designation("Accountant")
                        .salary(new BigDecimal("62000.00")).joiningDate(LocalDate.of(2023, 3, 10))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Daniel").lastName("Taylor")
                        .email("daniel.taylor@example.com").phoneNumber("+1777666555")
                        .department("Engineering").designation("Senior Software Engineer")
                        .salary(new BigDecimal("115000.00")).joiningDate(LocalDate.of(2021, 1, 10))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Amelia").lastName("Moore")
                        .email("amelia.moore@example.com").phoneNumber("+1666555444")
                        .department("Marketing").designation("Content Writer")
                        .salary(new BigDecimal("58000.00")).joiningDate(LocalDate.of(2023, 12, 1))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Henry").lastName("Jackson")
                        .email("henry.jackson@example.com").phoneNumber("+1555333222")
                        .department("Sales").designation("Sales Coordinator")
                        .salary(new BigDecimal("48000.00")).joiningDate(LocalDate.of(2024, 3, 15))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Evelyn").lastName("Martin")
                        .email("evelyn.martin@example.com").phoneNumber("+1333222111")
                        .department("Finance").designation("Auditor")
                        .salary(new BigDecimal("80000.00")).joiningDate(LocalDate.of(2021, 11, 1))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Sebastian").lastName("Lee")
                        .email("sebastian.lee@example.com").phoneNumber("+1111222333")
                        .department("Engineering").designation("Engineering Manager")
                        .salary(new BigDecimal("145000.00")).joiningDate(LocalDate.of(2019, 8, 20))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Harper").lastName("Perez")
                        .email("harper.perez@example.com").phoneNumber("+1444666888")
                        .department("Human Resources").designation("HR Specialist")
                        .salary(new BigDecimal("65000.00")).joiningDate(LocalDate.of(2022, 8, 1))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Jack").lastName("Thompson")
                        .email("jack.thompson@example.com").phoneNumber("+1555777999")
                        .department("Sales").designation("Sales Director")
                        .salary(new BigDecimal("130000.00")).joiningDate(LocalDate.of(2018, 5, 10))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Eleanor").lastName("White")
                        .email("eleanor.white@example.com").phoneNumber("+1666888000")
                        .department("Marketing").designation("SEO Analyst")
                        .salary(new BigDecimal("52000.00")).joiningDate(LocalDate.of(2024, 1, 15))
                        .status(EmployeeStatus.ACTIVE).build(),
                    
                    Employee.builder()
                        .firstName("Owen").lastName("Harris")
                        .email("owen.harris@example.com").phoneNumber("+1999777555")
                        .department("Finance").designation("Tax Specialist")
                        .salary(new BigDecimal("76000.00")).joiningDate(LocalDate.of(2022, 9, 15))
                        .status(EmployeeStatus.INACTIVE).build()
                ));
                System.out.println("H2 Database scaled successfully with 25 sample employee records!");
            }
        };
    }
}
