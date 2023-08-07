package Sujith.EmployeeService.Service;

import Sujith.EmployeeService.DTO.APIResponseDto;
import Sujith.EmployeeService.DTO.EmployeeDto;

public interface EmployeeService
{
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
}
