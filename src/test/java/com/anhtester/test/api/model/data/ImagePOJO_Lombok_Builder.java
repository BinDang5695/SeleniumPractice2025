package com.anhtester.test.api.model.data;

import com.anhtester.helpers.SystemHelper;
import com.anhtester.test.api.model.CategoryPOJO_Request_Lombok;
import net.datafaker.Faker;

import java.io.File;
import java.util.Locale;

public class ImagePOJO_Lombok_Builder {

    public static File getImageFile(String fileName) {
        return new File(SystemHelper.getCurrentDir() + "/src/test/resources/testdata/" + fileName);
    }

}