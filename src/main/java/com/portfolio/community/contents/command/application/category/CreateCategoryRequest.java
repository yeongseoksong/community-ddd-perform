package com.portfolio.community.contents.command.application.category;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CreateCategoryRequest {

    @NotBlank
    @Schema(description = "카테고리 명",example = "자유게시판")
    String name;

}
