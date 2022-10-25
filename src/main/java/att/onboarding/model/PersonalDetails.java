package att.onboarding.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "personal_details")
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long personal_details_id;

    @Column (name = "full_name")
    private String fullName;

    @Column (name = "call_name")
    private String callName;

    @Column (name = "date_of_birth")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "personalDetails")
    private Employee employee;

}
