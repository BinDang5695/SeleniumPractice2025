package test.api.model.data;

import test.api.model.RegisterUserPOJO_Lombok;
import net.datafaker.Faker;

import java.util.Locale;

public class UserPOJO_Lombok_Builder {

    private static final Faker faker = new Faker(new Locale("en"));

    public static RegisterUserPOJO_Lombok getDataToCreateUser() {
        return RegisterUserPOJO_Lombok.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.regexify("[A-Z][a-z]{3,6}[0-9]{2,4}[!@#$]"))
                .phone(faker.number().digits(10))
                .userStatus(1)
                .build();
    }

    public static RegisterUserPOJO_Lombok createUpdatedUser(RegisterUserPOJO_Lombok oldUser) {
        return RegisterUserPOJO_Lombok.builder()
                .id(oldUser.getId())
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.regexify("[A-Z][a-z]{3,6}[0-9]{2,4}[!@#$]"))
                .phone(faker.number().digits(10))
                .userStatus(1)
                .build();
    }

}