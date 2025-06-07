package com.portfolio.community.contents.command.application.post;


import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.post.Author;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class PostRequest {



    @NotBlank
    @Schema(description = "게시글 제목",example = "안녕하세요.")
    private String title;

    @NotBlank
    @Schema(description = "게시글 내용",example = "안녕하세요.반갑습니다.")
    private String content;

    @NotNull
    @Schema(description = "게시글 유료 여부",example = "false")
    private Boolean isPremium;

    @NotNull
    @Schema(description = "게시글 카테고리",example = "자유 게시판")
    private CategoryId categoryId;

}
