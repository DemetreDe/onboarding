package att.onboarding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contact_details")
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "contact_details_id")
    private long contactDetailsID;
    @Column (name = "personal_number")
    private String personalNumber;
    @Column (name = "land_line")
    private String landLine;
    @Column (name = "emergency_contact_name")
    private String emergencyContactName;
    @Column (name = "emergency_contact_relation")
    private String emergencyContactRelation;
    @Column (name = "emergency_contact_number")
    private String emergencyContactNumber;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "contactDetails")
    private Employee employee;


}
