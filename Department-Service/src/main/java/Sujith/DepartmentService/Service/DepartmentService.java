package Sujith.DepartmentService.Service;

import Sujith.DepartmentService.DTO.DepartmentDto;


public interface DepartmentService
{
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String code);
}
