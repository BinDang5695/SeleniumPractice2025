package test.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImagePOJO_Response_Lombok {
    private String message;
    private int id;
    private String path;
}
