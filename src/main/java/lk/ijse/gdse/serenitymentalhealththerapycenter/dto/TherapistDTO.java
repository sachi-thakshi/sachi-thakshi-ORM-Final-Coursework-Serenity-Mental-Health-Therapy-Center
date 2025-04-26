package lk.ijse.gdse.serenitymentalhealththerapycenter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TherapistDTO {

    private String therapistId;
    private String name;
    private String specialization;
    private String availability;
    private int contactNumber;
    private String assignedProgram;

}
