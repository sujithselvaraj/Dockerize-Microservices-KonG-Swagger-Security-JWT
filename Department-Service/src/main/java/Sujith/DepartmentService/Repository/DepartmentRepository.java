package Sujith.DepartmentService.Repository;

import Sujith.DepartmentService.DepartmentServiceApplication;
import Sujith.DepartmentService.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepartmentRepository extends JpaRepository<Department,Long>
{
    Department findByDepartmentCode(String departmentCode);
}
