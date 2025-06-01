package com.portfolio.community.contents.command.application;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {

    private String title;
    private String content;
    private Boolean isPremium;
    private String categoryId;

}
