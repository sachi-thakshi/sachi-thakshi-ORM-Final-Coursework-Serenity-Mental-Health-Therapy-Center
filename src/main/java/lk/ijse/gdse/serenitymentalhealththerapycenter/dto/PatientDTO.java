package lk.ijse.gdse.serenitymentalhealththerapycenter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PatientDTO {

    private String id;
    private String name;
    private String medicalHistory;
    private int contactNumber;
}
