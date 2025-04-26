package lk.ijse.gdse.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "therapists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Therapist {

    @Id
    @Column(name = "therapist_id", length = 10)
    private String therapistId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String specialization;

    @Column(nullable = false, length = 50)
    private String availability;

    @Column(nullable = false, length = 15)
    private int contactNumber;

    @Column(length = 100)
    private String assignedProgram ;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions = new ArrayList<>();

    public Therapist(String therapistId, String therapistName, String specialization, String availability, int contact, String program) {
        this.therapistId = therapistId;
        this.name = therapistName;
        this.specialization = specialization;
        this.availability = availability;
        this.contactNumber = contact;
        this.assignedProgram = program;
    }
}