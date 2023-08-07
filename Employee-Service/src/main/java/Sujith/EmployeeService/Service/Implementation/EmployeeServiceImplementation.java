package Sujith.EmployeeService.Service.Implementation;

import Sujith.EmployeeService.DTO.APIResponseDto;
import Sujith.EmployeeService.DTO.DepartmentDto;
import Sujith.EmployeeService.DTO.EmployeeDto;
import Sujith.EmployeeService.Entity.Employee;
import Sujith.EmployeeService.Repository.EmployeeRepository;
import Sujith.EmployeeService.Service.EmployeeService;
import Sujith.EmployeeService.Entity.Token;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService
{
    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee=new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee saveEmployee=employeeRepository.save(employee);

        EmployeeDto saveEmployeeDto=new EmployeeDto(
                saveEmployee.getId(),
                saveEmployee.getFirstName(),
                saveEmployee.getLastName(),
                saveEmployee.getEmail(),
                saveEmployee.getDepartmentCode()
        );



        return saveEmployeeDto;
    }


    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee=employeeRepository.findById(employeeId).get();

//        ResponseEntity<DepartmentDto> responseEntity= restTemplate.getForEntity("http://localhost:8081/"+employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto=responseEntity.getBody();
        HttpHeaders headers = new HttpHeaders();
        Token n = Token.getInstance();
        String jwt=n.getToken();
        headers.set("Authorization", jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        System.out.println(headers);
        String url="http://appp:8081/"+employee.getDepartmentCode();
        ResponseEntity<DepartmentDto> responseEntity= restTemplate.exchange(url, HttpMethod.GET,entity, DepartmentDto.class);
        DepartmentDto departmentDto=responseEntity.getBody();
        System.out.println(departmentDto);


        EmployeeDto employeeDto=new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto=new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }


}