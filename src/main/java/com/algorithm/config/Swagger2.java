package com.algorithm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger2
 * @Description TODO
 * @Author renhao
 * @Date 2019/12/19 12:58
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Java常用算法手册 （第三版）学习手册")
                .description("Java常用算法手册 （第三版）学习手册。")
                .contact("任好萌")
                .version("1.0")
                .build();

        return new Docket( DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                //以扫描包的方式
                .apis( RequestHandlerSelectors.basePackage("com.algorithm"))
                .paths( PathSelectors.any())
                .build();
    }

}