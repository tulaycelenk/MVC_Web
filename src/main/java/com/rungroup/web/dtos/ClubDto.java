package com.rungroup.web.dtos;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
public class ClubDto {
    private Long id;
    private String title;
    private String photoUrl;
    private String content;
    private String cratedOn;
    private String updatedOn;

}
