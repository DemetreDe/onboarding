package att.onboarding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Identity {
    @JsonProperty("ID")
    private long ID;
}

