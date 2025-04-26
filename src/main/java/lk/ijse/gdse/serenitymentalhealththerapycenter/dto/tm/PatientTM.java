package lk.ijse.gdse.serenitymentalhealththerapycenter.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PatientTM {

    private String id;
    private String name;
    private int contactNumber;
    private int age;
    private String medicalHistory;
}
