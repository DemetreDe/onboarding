package att.onboarding.controller;

import att.onboarding.model.*;
import att.onboarding.service.UserService;
import att.onboarding.utilities.Utilities;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/addChild")
    public ResponseEntity<Employee> addChild(@RequestBody ChildDetails childDetails,
                                             @RequestHeader(value = "Authorization") String jwtToken) {
        return new ResponseEntity<Employee>(userService.addChild(childDetails, jwtToken), HttpStatus.CREATED);
    }

    @PostMapping("/removeChild")
    public ResponseEntity<Employee> removeChild(@RequestBody Long id,
                                                @RequestHeader(value = "Authorization") String jwtToken) {
        return new ResponseEntity<Employee>(userService.removeChild(id, jwtToken), HttpStatus.CREATED);
    }

    @PostMapping("/editFamily")
    public ResponseEntity<Employee> editFamilyDetails(@RequestBody FamilyDetails familyDetails,
                                                      @RequestHeader(value = "Authorization") String jwtToken) {
        return new ResponseEntity<Employee>(userService.editFamilyDetails(familyDetails, jwtToken), HttpStatus.CREATED);
    }

    @PostMapping("/editContacts")
    public ResponseEntity<Employee> editContactDetails(@RequestBody ContactDetails contactDetails,
                                                       @RequestHeader(value = "Authorization") String jwtToken) {
        return new ResponseEntity<Employee>(userService.editContactDetails(contactDetails, jwtToken), HttpStatus.CREATED);
    }

    @PostMapping("/addProfession")
    public ResponseEntity<Employee> addPastProfessions(@RequestBody PastProfessions pastProfession,
                                                       @RequestHeader(value = "Authorization") String jwtToken) {
        return new ResponseEntity<Employee>(userService.addPastProfessions(pastProfession, jwtToken), HttpStatus.CREATED);
    }

    @PostMapping("/removeProfession")
    public ResponseEntity<Employee> deletePastProfession(@RequestBody Long id,
                                                         @RequestHeader(value = "Authorization") String jwtToken) {
        return new ResponseEntity<Employee>(userService.deletePastProfession(id, jwtToken), HttpStatus.CREATED);
    }

    @PostMapping("/editPersonal")
    public ResponseEntity<Employee> editPersonalDetails(@RequestBody PersonalDetails personalDetails,
                                                        @RequestHeader(value = "Authorization") String jwtToken) {
        return new ResponseEntity<Employee>(userService.editPersonalDetails(personalDetails, jwtToken), HttpStatus.CREATED);
    }


}
