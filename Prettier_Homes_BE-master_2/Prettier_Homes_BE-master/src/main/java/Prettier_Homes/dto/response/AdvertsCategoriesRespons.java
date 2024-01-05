package Prettier_Homes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertsCategoriesRespons {

    private String categoryName;
    private Long categoryId;
    private Long amount;
}