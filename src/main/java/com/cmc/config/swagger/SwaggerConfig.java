package com.cmc.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        // 一个简单的任务执行时间监视器
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo());
        ApiSelectorBuilder selectorBuilder = new ApiSelectorBuilder(docket);
        selectorBuilder.apis(RequestHandlerSelectors.basePackage("com.cmc.web"));
        selectorBuilder.paths(PathSelectors.any());
        docket = selectorBuilder.build();
        return docket;
    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder infoBuilder = new ApiInfoBuilder();
        infoBuilder.title("Spring中使用Swagger2构建RESTful APIs.");
        infoBuilder.description("客户端与服务端接口文档.");
        // infoBuilder.contact("");
        // infoBuilder.license("Apache 2.0");
        // infoBuilder.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        // infoBuilder.version("1.0.0");
        return infoBuilder.build();
    }

}