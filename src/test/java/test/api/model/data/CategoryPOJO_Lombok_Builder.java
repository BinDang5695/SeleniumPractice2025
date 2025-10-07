package test.api.model.data;

import test.api.model.CategoryPOJO_Request_Lombok;
import net.datafaker.Faker;

import java.util.Locale;

public class CategoryPOJO_Lombok_Builder {

    private static final Faker faker = new Faker(new Locale("en"));

    public static CategoryPOJO_Request_Lombok getDataToCreateCategory() {
        return CategoryPOJO_Request_Lombok.builder()
                .name(faker.name().name())
                .build();
    }

    public static CategoryPOJO_Request_Lombok createUpdatedCategory(CategoryPOJO_Request_Lombok categoryrequest) {
        return CategoryPOJO_Request_Lombok.builder()
                .id(categoryrequest.getId())
                .name(faker.name().name())
                .build();
    }

}