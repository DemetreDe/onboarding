package att.onboarding.repository;

import att.onboarding.model.ChildDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildDetailsRepository extends JpaRepository<ChildDetails, Long> {
}
