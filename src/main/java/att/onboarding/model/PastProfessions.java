package att.onboarding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "past_professions")
@NoArgsConstructor
@AllArgsConstructor
public class PastProfessions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "profession_id")
    private long professionId;
    @Column (name = "designation")
    private String designation;

    @Column (name = "start_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @Column (name = "end_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

}

