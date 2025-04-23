package lk.ijse.gdse.serenitymentalhealththerapycenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TherapyProgramTM {

    private String programId;
    private String name;
    private String duration;
    private BigDecimal fee;
    private String therapist;
    private String description;

}
