package Sujith.EmployeeService.Controller;

import Sujith.EmployeeService.DTO.APIResponseDto;
import Sujith.EmployeeService.DTO.EmployeeDto;
import Sujith.EmployeeService.Service.EmployeeService;
import Sujith.EmployeeService.Entity.Token;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class EmployeeController
{
    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto)
    {
       EmployeeDto saveEmployee= employeeService.saveEmployee(employeeDto);
       return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long employeeId,@RequestHeader String token)
    {
        Token n = Token.getInstance();

        n.setToken(token);

        APIResponseDto apiResponseDto=employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }

}
