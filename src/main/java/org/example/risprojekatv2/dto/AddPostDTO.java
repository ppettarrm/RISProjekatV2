package org.example.risprojekatv2.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddPostDTO {

    private String description;
    private MultipartFile image;
}
