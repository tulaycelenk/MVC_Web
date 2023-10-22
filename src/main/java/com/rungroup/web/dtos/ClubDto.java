package com.rungroup.web.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDto {
    private Long id;
    @NotEmpty(message = "Club title shouldn't be empty")
    private String title;
    @NotEmpty(message = "Photo url shouldn't be empty")
    private String photoUrl;
    @NotEmpty(message = "Content shouldn't be empty")
    private String content;
    private String cratedOn;
    private String updatedOn;

}
