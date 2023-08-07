package Sujith.EmployeeService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto
{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentCode;

}
