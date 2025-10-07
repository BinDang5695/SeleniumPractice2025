package test.api.model;

import lombok.*;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImagePOJO_Request_Lombok {
    private File image;
}