package att.onboarding.repository;

import att.onboarding.model.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails,Long> {
}
