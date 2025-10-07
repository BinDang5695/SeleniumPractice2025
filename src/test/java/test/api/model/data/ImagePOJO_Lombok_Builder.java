package test.api.model.data;

import settings.helpers.SystemHelper;

import java.io.File;

public class ImagePOJO_Lombok_Builder {

    public static File getImageFile(String fileName) {
        return new File(SystemHelper.getCurrentDir() + "/src/test/resources/testdata/" + fileName);
    }

}