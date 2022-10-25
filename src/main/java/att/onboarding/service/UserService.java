package att.onboarding.service;

import att.onboarding.model.*;
import com.auth0.jwt.JWT;

import java.util.List;

public interface UserService {

    Employee addChild(ChildDetails childDetails ,String jwt);

    Employee removeChild(Long id ,String jwt);

    Employee editFamilyDetails(FamilyDetails familyDetails ,String jwt);

    Employee editContactDetails(ContactDetails contactDetails ,String jwt);

    Employee addPastProfessions(PastProfessions pastProfessions ,String jwt);

    Employee deletePastProfession(Long id ,String jwt);

    Employee editPersonalDetails(PersonalDetails personalDetails, String jwt);

}
