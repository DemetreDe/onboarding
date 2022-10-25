package att.onboarding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "family_details")
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="family_id")
    private long familyId;

    @Column (name="marital_status")
    private Boolean maritalStatus;

    @Column (name="spouse_name")
    private String spouseName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_id" , referencedColumnName = "family_id")
    private List<ChildDetails> children = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "familyDetails")
    private Employee employee;

}
