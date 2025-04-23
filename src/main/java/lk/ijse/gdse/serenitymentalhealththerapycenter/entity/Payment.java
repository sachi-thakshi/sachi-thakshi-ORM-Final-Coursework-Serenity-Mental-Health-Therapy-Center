package lk.ijse.gdse.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Payment {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id" , length=10)
    private String id;

    @Column(nullable = false, length = 20)
    private String sessionId;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 20)
    private String paymentMethod;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(length = 200)
    private String status;

    private BigDecimal paidAmount;

    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "program_id", nullable = false)
    private TherapyProgram therapyProgram;
}