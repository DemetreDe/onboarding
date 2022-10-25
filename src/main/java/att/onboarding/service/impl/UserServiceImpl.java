package att.onboarding.service.impl;

import att.onboarding.model.*;
import att.onboarding.repository.*;
import att.onboarding.service.UserService;
import att.onboarding.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final EmployeeRepository employeeRepository;

    private Employee employee;

    private Utilities utilities = new Utilities();

    public void setEmployee(String jwt){
        String email = this.utilities.tokenToSubject(jwt);
        this.employee = employeeRepository.findByEmail(email);
    }

    @Override
    public Employee addChild(ChildDetails childDetails ,String jwt) {
        setEmployee(jwt);
        employee.getFamilyDetails().getChildren().add(childDetails);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee removeChild(Long id ,String jwt){
        setEmployee(jwt);
        List<ChildDetails> children = employee.getFamilyDetails().getChildren();
        ChildDetails childDetails;
        for (int i = 0; i < children.size() ; i++) {
            childDetails = children.get(i);
            if(id.equals(childDetails.getChildId())){
                children.remove(i);
                employee.getFamilyDetails().setChildren(children);
                return employeeRepository.save(employee);
            }
        }
        //TODO exception handling
        log.info("Child not found");
        return null;
    }

    @Override
    public Employee editFamilyDetails(FamilyDetails familyDetails ,String jwt) {
        setEmployee(jwt);
        FamilyDetails oldFamilyDetails = employee.getFamilyDetails();
        oldFamilyDetails.setMaritalStatus(familyDetails.getMaritalStatus());
        oldFamilyDetails.setSpouseName(familyDetails.getSpouseName());
        employee.setFamilyDetails(oldFamilyDetails);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee editContactDetails(ContactDetails contactDetails ,String jwt) {
        setEmployee(jwt);
        ContactDetails oldContactDetails = employee.getContactDetails();
        oldContactDetails.setEmergencyContactName(contactDetails.getEmergencyContactName());
        oldContactDetails.setEmergencyContactNumber(contactDetails.getEmergencyContactNumber());
        oldContactDetails.setEmergencyContactRelation(contactDetails.getEmergencyContactRelation());
        oldContactDetails.setLandLine(contactDetails.getLandLine());
        oldContactDetails.setPersonalNumber(contactDetails.getPersonalNumber());
        employee.setContactDetails(oldContactDetails);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee addPastProfessions(PastProfessions pastProfession,String jwt) {
        setEmployee(jwt);
        employee.getPastProfessions().add(pastProfession);
        return employeeRepository.save(employee);
    }

    public Employee deletePastProfession(Long id,String jwt){
        setEmployee(jwt);
        List<PastProfessions> pastProfessions = employee.getPastProfessions();
        PastProfessions pastProfession;
        for (int i = 0; i < pastProfessions.size() ; i++) {
            pastProfession = pastProfessions.get(i);
            if(id.equals(pastProfession.getProfessionId())){
                pastProfessions.remove(i);
                employee.setPastProfessions(pastProfessions);
                return employeeRepository.save(employee);
            }
        }
        //TODO exception handling
        log.info("Profession not found");
        return null;
    }

    @Override
    public Employee editPersonalDetails(PersonalDetails personalDetails, String jwt) {
        setEmployee(jwt);
        PersonalDetails oldPersonalDetails = employee.getPersonalDetails();
        oldPersonalDetails.setDob(personalDetails.getDob());
        oldPersonalDetails.setCallName(personalDetails.getCallName());
        oldPersonalDetails.setFullName(personalDetails.getFullName());
        employee.setPersonalDetails(oldPersonalDetails);
        return employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);

        if(employee == null) {
            log.error("User not found in db");
            throw new UsernameNotFoundException("User not found in db");
        } else {
            log.info("user found : {}", employee.getFirstname());
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        employee.getAuthorizationLevel().forEach(authorizationLevel -> {
            authorities.add(new SimpleGrantedAuthority(authorizationLevel.getName()));
        });

        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), authorities);
    }
}
