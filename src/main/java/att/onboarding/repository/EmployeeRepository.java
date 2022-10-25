package att.onboarding.repository;

import att.onboarding.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getEmployeeByEmployeeId(Long id);

    Employee findByEmail(String email);

}
