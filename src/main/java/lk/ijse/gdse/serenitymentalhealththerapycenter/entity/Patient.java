package lk.ijse.gdse.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 1000)
    private String medicalHistory;

    @Column(nullable = false, length = 15)
    private int contactNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    public Patient(String id, String name, String medicalHistory, int contactNumber) {
        this.id = id;
        this.name = name;
        this.medicalHistory = medicalHistory;
        this.contactNumber = contactNumber;
    }
}