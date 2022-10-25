package att.onboarding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@Table(name = "child_details")
@NoArgsConstructor
@AllArgsConstructor
public class ChildDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_id")
    private long childId;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;

}