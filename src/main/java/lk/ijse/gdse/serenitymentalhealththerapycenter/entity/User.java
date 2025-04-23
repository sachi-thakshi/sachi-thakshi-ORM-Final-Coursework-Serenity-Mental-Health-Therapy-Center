package lk.ijse.gdse.serenitymentalhealththerapycenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class User {
    @Id
    private String userId;
    private String userName;
    private String password;
    private String role;

}