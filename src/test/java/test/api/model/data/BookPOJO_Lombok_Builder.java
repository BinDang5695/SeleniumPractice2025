package test.api.model.data;

import test.api.model.BookPOJO_Lombok;
import net.datafaker.Faker;

import java.util.Collections;
import java.util.Locale;

public class BookPOJO_Lombok_Builder {

    private static final Faker faker = new Faker(new Locale("en"));

    public static BookPOJO_Lombok getDataToCreateBook() {
        return BookPOJO_Lombok.builder()
                .name(faker.book().title())
                .category_id(139)
                .price(faker.number().numberBetween(1, 9999))
                .release_date(
                        faker.timeAndDate()
                                .birthday(18, 80)
                                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                )
                .status(true)
                .image_ids(Collections.singletonList(76))
                .build();
    }

    public static BookPOJO_Lombok createUpdatedBook(BookPOJO_Lombok request) {
        return BookPOJO_Lombok.builder()
                .id(request.getId())
                .name(faker.book().title())
                .category_id(request.getCategory_id())
                .price(request.getPrice())
                .release_date(request.getRelease_date())
                .status(false)
                .image_ids(Collections.singletonList(61))
                .build();
    }




}