package lk.ijse.gdse.serenitymentalhealththerapycenter.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserDTO {

    private String userId;
    private String userName;
    private String password;
    private String role;

}