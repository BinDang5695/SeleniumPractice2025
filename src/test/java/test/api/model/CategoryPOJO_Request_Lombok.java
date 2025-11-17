package test.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryPOJO_Request_Lombok {

    private String name;
    private String message;
    private int id;

}