package Sujith.DepartmentService.Controller;

import Sujith.DepartmentService.DTO.DepartmentDto;
import Sujith.DepartmentService.Entity.JWTRequest;
import Sujith.DepartmentService.Entity.JWTResponse;
import Sujith.DepartmentService.Service.DepartmentService;
import Sujith.DepartmentService.Service.UserService;
import Sujith.DepartmentService.Utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin(origins = "http://localhost:8082")
public class DepartmentController
{
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto)
    {
        DepartmentDto savedDepartment=departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode)
    {
       DepartmentDto departmentDto= departmentService.getDepartmentByCode(departmentCode);
       return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );


            final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
            String token = null;

            if (userDetails.getUsername().equals (jwtRequest.getUsername())) {
                token = jwtUtility.generateToken(userDetails);

//                token = jwtUtility.generateToken(claims, userDetails.getUsername(), expiration);

            }
            return new JWTResponse(token);

        }
        catch (BadCredentialsException e)
        {
            throw new Exception("INVALID_CREDENTIALS",e);
        }


    }

}
