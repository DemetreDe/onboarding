package att.onboarding.controller;


import att.onboarding.model.Employee;
import att.onboarding.model.Identity;
import att.onboarding.model.Status;
import att.onboarding.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//Controller for admins
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        super();
        this.adminService = adminService;
    }



    @PostMapping("/employee/create")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws NoSuchAlgorithmException {
        //TODO Create authentication setup thing
        return new ResponseEntity<Employee>(adminService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PostMapping("/employee/delete")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee (@RequestBody Identity identity){
        Long id = identity.getID();
        return new ResponseEntity<Map<String, Boolean>>(adminService.deleteEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/employee/status")
    public ResponseEntity<Map<String, Boolean>> changeEmployeeStatus(@RequestBody Identity identity,
                                                                     Status status)
    {
        return new ResponseEntity<>(adminService.setEmployeeStatus(identity, status), HttpStatus.OK);
    }

}
