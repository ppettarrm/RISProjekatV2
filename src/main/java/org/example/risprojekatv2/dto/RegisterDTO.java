package org.example.risprojekatv2.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterDTO {

    private String username;
    private String password;
    private MultipartFile userImage;
    private String description;
    private String mail;

}
