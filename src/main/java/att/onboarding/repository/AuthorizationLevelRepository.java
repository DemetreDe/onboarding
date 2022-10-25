package att.onboarding.repository;

import att.onboarding.model.AuthorizationLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationLevelRepository extends JpaRepository<AuthorizationLevel, Long> {

    AuthorizationLevel findByName(String name);

}
