package Sujith.EmployeeService.Repository;

import Sujith.EmployeeService.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long>
{

}
