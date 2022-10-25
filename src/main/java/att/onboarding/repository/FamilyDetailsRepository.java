package att.onboarding.repository;

import att.onboarding.model.FamilyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyDetailsRepository extends JpaRepository<FamilyDetails, Long> {
}
