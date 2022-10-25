package att.onboarding.repository;

import att.onboarding.model.PastProfessions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastProfessionsRepository extends JpaRepository<PastProfessions,Long> {
}
