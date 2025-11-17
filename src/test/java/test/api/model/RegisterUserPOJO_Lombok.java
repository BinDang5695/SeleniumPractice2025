package test.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class RegisterUserPOJO_Lombok {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
    private int id;








}