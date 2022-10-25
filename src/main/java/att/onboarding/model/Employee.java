package att.onboarding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.EAGER;


@Data
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;
    @Column (name="firstname")
    private String firstname;
    @Column (name="lastname")
    private String lastname;
    @Column (name="nic")
    private String NIC;
    @Column (name="email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column (name="status")
    private Status status;

    @ManyToMany(fetch = EAGER)
    @JsonIgnore
    private Collection<AuthorizationLevel> authorizationLevel = new ArrayList<>();

    @Column (name = "auth_token")
    private String authenticationToken;

    @Column (name="password")
    private String password;

    @Column (name="last_login")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date last_login;

    @OneToOne(fetch = EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_details_id")
    @JsonIgnore
    private PersonalDetails personalDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "family_id")
    @JsonIgnore
    private FamilyDetails familyDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_details_id")
    @JsonIgnore
    private ContactDetails contactDetails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "profession_id" , referencedColumnName = "employee_id")
    @JsonIgnore
    private List<PastProfessions> pastProfessions = new ArrayList<>();

}
