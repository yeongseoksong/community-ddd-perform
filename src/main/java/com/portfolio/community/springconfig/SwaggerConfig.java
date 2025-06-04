package com.portfolio.community.springconfig;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .group("api") // API 그룹 이름 설정
                .pathsToMatch("/api/**") // 포함될 엔드포인트 설정
                .pathsToExclude("")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(new Info()
                            .title("community") // API 제목
                            .description("community REST API") // API 설명
                            .version("1.0.0") // API 버전
                    );
                    // JWT 인증 스키마 및 요구 사항 추가
//                    openApi.components(new Components().addSecuritySchemes("APIKey", jwtSecurityScheme()))
//                            .addSecurityItem(new SecurityRequirement().addList("APIKey"));

                })
                .build();
    }




}