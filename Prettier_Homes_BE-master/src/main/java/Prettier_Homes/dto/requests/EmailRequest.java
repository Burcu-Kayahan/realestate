package Prettier_Homes.dto.requests;

import Prettier_Homes.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class EmailRequest {


    private String email;
    private String subject;
    private List<UserDto> users;
    private List<String> filePath;


}
