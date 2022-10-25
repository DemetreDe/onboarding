package att.onboarding.service.impl;

import att.onboarding.model.*;
import att.onboarding.repository.AuthorizationLevelRepository;
import att.onboarding.repository.EmployeeRepository;
import att.onboarding.service.AdminService;
import att.onboarding.utilities.EmailSender;
import att.onboarding.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//TODO GET JWT FOR ADMIN THEN DO SOMETHING WITH IT IN RELATION TO LOGGING THEIR CHANGES

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    private final EmployeeRepository employeeRepository;
    private final AuthorizationLevelRepository authorizationLevelRepository;
    private final PasswordEncoder passwordEncoder;
    private final Utilities utilities;
    private final EmailSender emailSender;

    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setStatus(Status.CREATED_INCOMPLETE);

        Collection<AuthorizationLevel> authorizationLevel = new ArrayList<>();
        authorizationLevel.add(getAuthorizationLevelDB("ROLE_USER"));
        employee.setAuthorizationLevel(authorizationLevel);

        ContactDetails contactDetails = new ContactDetails();
        FamilyDetails familyDetails = new FamilyDetails();
        PersonalDetails personalDetails = new PersonalDetails();

        employee.setContactDetails(contactDetails);
        employee.setFamilyDetails(familyDetails);
        employee.setPersonalDetails(personalDetails);

        employee.setLast_login(utilities.getDate());
        emailSender.sendAuthenticationEmail(employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Map<String, Boolean> deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);
        this.employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeByEmployeeId(id);
    }

    @Override
    public Map<String, Boolean> setEmployeeStatus(Identity id, Status status) {
        Employee employee = employeeRepository.getEmployeeByEmployeeId(id.getID());
        employee.setStatus(status);
        employeeRepository.save(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put( employee.getFirstname() + " status to " + status, Boolean.TRUE);

        return response;
    }


    @Override
    public AuthorizationLevel saveAuthorizationLevel(AuthorizationLevel authorizationLevel) {
        return authorizationLevelRepository.save(authorizationLevel);
    }

    @Override
    public AuthorizationLevel getAuthorizationLevelDB(String roleName) {
        AuthorizationLevel authorizationLevel = authorizationLevelRepository.findByName(roleName);
        return authorizationLevel;
    }

    @Override
    public Employee makeAdmin(String email) {
        Employee employee = getEmployeeByEmail(email);
        employee.getAuthorizationLevel().add(getAuthorizationLevelDB("ROLE_ADMIN"));
        return employeeRepository.save(employee);
    }


}
