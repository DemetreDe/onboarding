package att.onboarding.service;

import att.onboarding.model.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface AdminService {

    Employee saveEmployee (Employee employee);

    Map<String, Boolean> deleteEmployee(Long id);

    Employee getEmployeeByEmail(String email);

    Employee getEmployeeById(Long id);

    Map<String, Boolean> setEmployeeStatus(Identity id , Status status);

    AuthorizationLevel saveAuthorizationLevel(AuthorizationLevel authorizationLevel);


    AuthorizationLevel getAuthorizationLevelDB(String roleName);

    Employee makeAdmin(String email);



}
