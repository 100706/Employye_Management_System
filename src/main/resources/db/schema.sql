-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS ems_db;
USE ems_db;

-- Drop table if it exists to allow clean re-runs during development
DROP TABLE IF EXISTS employees;

-- Create employees table
CREATE TABLE employees (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    department VARCHAR(50) NOT NULL,
    designation VARCHAR(50) NOT NULL,
    salary DECIMAL(12, 2) NOT NULL,
    joining_date DATE NOT NULL,
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample records for demonstration/testing
INSERT INTO employees (first_name, last_name, email, phone_number, department, designation, salary, joining_date, status) VALUES
('John', 'Doe', 'john.doe@example.com', '+1234567890', 'Engineering', 'Software Engineer', 85000.00, '2023-01-15', 'ACTIVE'),
('Jane', 'Smith', 'jane.smith@example.com', '+1987654321', 'Human Resources', 'HR Manager', 75000.00, '2022-06-10', 'ACTIVE'),
('Bob', 'Johnson', 'bob.johnson@example.com', '+1122334455', 'Engineering', 'Tech Lead', 120000.00, '2020-03-22', 'ACTIVE'),
('Alice', 'Williams', 'alice.williams@example.com', '+1555444333', 'Marketing', 'Marketing Specialist', 65000.00, '2024-02-01', 'INACTIVE');
