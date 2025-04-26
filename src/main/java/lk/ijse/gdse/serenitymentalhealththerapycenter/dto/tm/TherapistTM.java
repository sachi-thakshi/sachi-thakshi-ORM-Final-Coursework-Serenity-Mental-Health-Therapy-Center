package lk.ijse.gdse.serenitymentalhealththerapycenter.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TherapistTM {

    private String therapistId;
    private String name;
    private String specialization;
    private String availability;
    private int contactNumber;
    private String assignedProgram;

}
