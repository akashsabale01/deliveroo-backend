package com.ecorp.deliveroo.modal.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {

    private String name;
    
    private String imageName;
    
    private String imageType;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

}
